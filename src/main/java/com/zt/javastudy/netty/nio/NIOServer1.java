package com.zt.javastudy.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author zhengtao on 2022/10/13
 */
public class NIOServer1 {
    public static void main(String[] args) throws IOException {
        // 打开管道，用于监听客户端的连接，它是所有客户端连接的父管道
        ServerSocketChannel channel = ServerSocketChannel.open();
        // 绑定监听端口，设置连接为非阻塞模式
        channel.socket().bind(new InetSocketAddress(8888));
        channel.configureBlocking(false);
        // 创建Reactor线程，创建多路复用器并启动线程
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_ACCEPT);
        new NIOServerThread(selector).start();

    }
}
