package com.fy.tutorial01.server;

import com.fy.tutorial01.util.DataTypeUtil;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiFunction;

@Slf4j
public class ReactorNettyTcpServerHandler {

    private Map<Connection, AtomicReference<ServerHandshakeState>> map = new ConcurrentHashMap<>();

    public BiFunction<NettyInbound, NettyOutbound, Publisher<Void>> handleInbound(){
        AtomicReference<ServerHandshakeState> handshakeState = new AtomicReference<>(ServerHandshakeState.SEND_INIT_REQ);
        return (nettyInbound, nettyOutbound) -> nettyInbound
                .receive()
                .asByteArray()
                .flatMap(bytes -> {
                    log.info("Server received HEX payload: {}", DataTypeUtil.bytesToHex(bytes));
                    if(handshakeState.get().receivedPayloadMatchesExpected(bytes)){
                        log.info("Server responding with HEX payload: {} ...",
                                DataTypeUtil.bytesToHex(handshakeState.get().getResponsePayload()));
                        nettyOutbound.sendByteArray(Mono.just(handshakeState.get().getResponsePayload()))
                                .then()
                                .subscribe();
                        if(handshakeState.get().getNextStateOrdinal() != null){
                            ServerHandshakeState nextState = ServerHandshakeState.values()[handshakeState.get().getNextStateOrdinal()];
                            log.info("Server handshake state will become: {}", nextState);
                            handshakeState.set(nextState);
                        }else {
                            log.info("Server has completed the custom handshake.");
                        }
                    }else{
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
