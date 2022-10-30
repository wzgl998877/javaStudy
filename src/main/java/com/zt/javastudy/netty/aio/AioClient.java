package com.zt.javastudy.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhengtao
 * @create 2022/10/30 17:53
 * aio 客户端
 */
public class AioClient implements CompletionHandler<Void, AioClient>, Runnable {
    private AsynchronousSocketChannel channel;
    private CountDownLatch latch;

    public AioClient(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        channel.connect(new InetSocketAddress("localhost", 8888), this, this);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AioClient attachment) {
        // 读取消息
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new ReadCompletionHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AioClient attachment) {
        exc.printStackTrace();
        latch.countDown();
    }

    private void doWrite(AsynchronousSocketChannel channel) throws IOException {
        // 写消息
        String msg = "服务端你好现在是:" + System.currentTimeMillis();
        byte[] resp = msg.getBytes(StandardCharsets.UTF_8);
        ByteBuffer writeBuffer = ByteBuffer.allocate(resp.length);
        writeBuffer.put(resp);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }

    public static void main(String[] args) throws IOException {
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        new Thread(new AioClient(channel)).start();
    }


}
