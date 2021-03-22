package com.fy.tutorial01.client;

import io.netty.channel.ChannelOption;
import reactor.core.publisher.Mono;
import reactor.netty.tcp.TcpClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReactorNettyTcpClientManager {

    private static Map<String, TcpClient> ipToTcpClientMap = new ConcurrentHashMap<>();

    public Mono<TcpClient> createClient(String ip){
        TcpClient tcpClient = TcpClient.create()
                .host("127.0.0.1")
                .port(23456)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .wiretap(true);
        ipToTcpClientMap.put(ip, tcpClient);
        return Mono.just(tcpClient);
    }

    public Mono<TcpClient> retrieveClient(String ip) {
        TcpClient client = ipToTcpClientMap.get(ip);

        if (client == null) {
            return Mono.empty();
        }

        return Mono.just(client);
    }
}
