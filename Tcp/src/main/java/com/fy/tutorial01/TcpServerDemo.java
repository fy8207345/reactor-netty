package com.fy.tutorial01;

import reactor.netty.DisposableServer;
import reactor.netty.tcp.TcpServer;

public class TcpServerDemo {

    public static void main(String[] args) {
        DisposableServer  server = TcpServer.create()
                .host("localhost")
                .port(8090)
                .bindNow();
        server.onDispose().block();
        System.out.println("started");
    }
}
