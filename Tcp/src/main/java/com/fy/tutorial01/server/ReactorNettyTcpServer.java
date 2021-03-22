package com.fy.tutorial01.server;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class ReactorNettyTcpServer {

    public static void main(String[] args) {

        ReactorNettyTcpServerHandler handler = new ReactorNettyTcpServerHandler();
        DisposableServer disposableServer = TcpServer.create()
                .host("127.0.0.1")
                .port(23456)
                .wiretap(true)
                .handle(handler.handleInbound())
                .noSSL()
                .bindNow();
        disposableServer.onDispose().block();
    }
}
