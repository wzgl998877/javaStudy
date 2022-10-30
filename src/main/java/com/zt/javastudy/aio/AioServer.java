package com.zt.javastudy.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhengtao
 * @create 2022/10/30 15:44
 */
public class AioServer implements Runnable {
    AsynchronousServerSocketChannel serverSocketChannel;
    CountDownLatch latch;

    public AioServer(AsynchronousServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        serverSocketChannel.accept(this, new AcceptCompletionHandler());
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doAccept() {
        serverSocketChannel.accept(this, new AcceptCompletionHandler());
    }

    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8888));
        new Thread(new AioServer(serverSocketChannel)).start();
        System.out.println("服务端启动了");
    }
}
