package com.zt.javastudy.aio;

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
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AioClient attachment) {
        String msg = "服务端你好现在是:" + System.currentTimeMillis();
        byte[] req = msg.getBytes(StandardCharsets.UTF_8);
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                if (buffer.hasRemaining()) {
                    channel.write(buffer, buffer, this);
                } else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    channel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer buffer) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            String body;
                            body = new String(bytes, StandardCharsets.UTF_8);
                            System.out.println("客户端收到服务端消息 : " + body);
                            latch.countDown();
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            try {
                                channel.close();
                                latch.countDown();
                            } catch (IOException e) {
                                // ingnore on close
                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                try {
                    channel.close();
                    latch.countDown();
                } catch (IOException e) {
                    // ingnore on close
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, AioClient attachment) {
        exc.printStackTrace();
        try {
            channel.close();
            latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // 打开客户端管道
        AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
        new Thread(new AioClient(channel)).start();
    }
}
