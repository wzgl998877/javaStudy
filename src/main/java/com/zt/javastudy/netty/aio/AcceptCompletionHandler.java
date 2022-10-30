package com.zt.javastudy.netty.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author zhengtao
 * @create 2022/10/30 16:08
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
    @Override
    public void completed(AsynchronousSocketChannel result, AioServer attachment) {
        // 再接收客户端连接
        attachment.serverSocketChannel.accept(attachment, this);

        // 读取消息
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.printStackTrace();
        attachment.latch.countDown();
    }
}
