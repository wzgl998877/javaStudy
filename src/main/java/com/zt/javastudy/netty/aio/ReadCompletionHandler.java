package com.zt.javastudy.netty.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

/**
 * @author zhengtao
 * @create 2022/10/30 16:53
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, StandardCharsets.UTF_8);
            System.out.println("服务端收到客户端消息:" + req);
            doWrite(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(AsynchronousSocketChannel channel) throws IOException {
        // 写消息
        String msg = "客户端你好现在是:" + System.currentTimeMillis();
        byte[] resp = msg.getBytes(StandardCharsets.UTF_8);
        ByteBuffer writeBuffer = ByteBuffer.allocate(resp.length);
        writeBuffer.put(resp);
        writeBuffer.flip();
        channel.write(writeBuffer);
    }
}
