package com.fy.tutorial01.client;

import com.fy.tutorial01.util.DataTypeUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.tcp.TcpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class ReactorNettyTcpClient {

    public static void main(String[] args) throws InterruptedException {
        ReactorNettyTcpClient reactorNettyTcpClient = new ReactorNettyTcpClient();
        reactorNettyTcpClient.start();
        TimeUnit.HOURS.sleep(10);
    }

    private void start(){
        ReactorNettyTcpClientManager manager = new ReactorNettyTcpClientManager();
        manager.retrieveClient("localhost")
                .switchIfEmpty(manager.createClient("localhost"))
                .flatMap(TcpClient::connect)
                .retryBackoff(3, Duration.ofSeconds(1))
                .flatMap(this::handleHandshake)
                .subscribe();
    }

    private Mono<Void> handleHandshake(Connection connection) {
        AtomicReference<ClientHandshakeState> handshakeState =
                new AtomicReference<>(ClientHandshakeState.SEND_INIT_REQ);
        log.info("Client is initiating custom handshake with payload: {} (HEX)",
                DataTypeUtil.bytesToHex(handshakeState.get().getPayload()));
        connection.outbound().sendByteArray(Mono.just(handshakeState.get().getPayload()))
                .then().subscribe();
        return connection.inbound().receive()
                .asByteArray()
                .flatMap(bytes -> {
                    log.info("Client received HEX payload: {}", DataTypeUtil.bytesToHex(bytes));
                    if (handshakeState.get().receivedPayloadMatchesExpected(bytes)) {
                        if (handshakeState.get().getNextStateOrdinal() != null) {
                            ClientHandshakeState nextState = ClientHandshakeState
                                    .values()[handshakeState.get().getNextStateOrdinal()];
                            log.info("Client handshake state will become: {}", nextState);
                            handshakeState.set(nextState);
                            log.info("Client is sending new request payload: {} (HEX)",
                                    DataTypeUtil.bytesToHex(handshakeState.get().getPayload()));
                            connection.outbound().sendByteArray(Mono.just(handshakeState.get().getPayload()))
                                    .then()
                                    .subscribe();
                        }else {
                            log.info("Client has completed the custom handshake.");
                        }
                    }else {
                        return Mono.error(new IllegalStateException("Data received is not valid!"));
                    }
                    return Mono.empty();
                })
                .onErrorResume(e -> {
                    log.error("Error occurred: {}", e.getMessage(), e);
                    return Mono.empty();
                })
                .then();
    }
}
