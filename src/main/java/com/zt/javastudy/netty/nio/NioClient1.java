package com.zt.javastudy.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author zhengtao on 2022/10/13
 */
public class NioClient1 {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        // 创建Reactor线程，创建多路复用器并启动线程
        Selector selector = Selector.open();
        boolean isConnect = channel.connect(new InetSocketAddress("localhost", 8888));
        if (isConnect) {
            channel.register(selector, SelectionKey.OP_READ);
        } else {
            channel.register(selector, SelectionKey.OP_CONNECT);
        }
        new NioClientThread(selector).start();
        System.out.println("客户端启动成功");
    }
}
