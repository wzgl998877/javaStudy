package com.zt.javastudy.nio;

import lombok.Data;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * nio服务端线程
 *
 * @author zhengtao on 2022/10/13
 */
@Data
public class NIOServerThread extends Thread {
    public NIOServerThread(Selector selector) {
        this.selector = selector;
    }

    private Selector selector;

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    handleInput(key);
                }
            } catch (Exception ignore) {
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            return;
        }

        // 服务端ServerSocketChannel
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("客户端连接了");
        }


        if (key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(readBuffer);
            readBuffer.flip();
            if (readBytes > 0) {
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String req = new String(bytes, StandardCharsets.UTF_8);
                System.out.println("服务端收到客户端消息:" + req);
                // 写消息
                String msg = "客户端你好我是:" + System.currentTimeMillis();
                byte[] resp = msg.getBytes(StandardCharsets.UTF_8);
                ByteBuffer writeBuffer = ByteBuffer.allocate(resp.length);
                writeBuffer.put(resp);
                writeBuffer.flip();
                socketChannel.write(writeBuffer);
            } else if (readBytes < 0) {
                key.cancel();
                socketChannel.close();
            }
        }
    }
}
