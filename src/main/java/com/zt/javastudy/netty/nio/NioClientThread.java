package com.zt.javastudy.netty.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhengtao on 2022/10/13
 */
@Slf4j
public class NioClientThread extends Thread {
    public NioClientThread(Selector selector) {
        this.selector = selector;
    }

    private final Selector selector;

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);  //Selects a set of keys whose corresponding channels are ready for I/O
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    handleInput(key);
                }
            } catch (Exception e) {
                log.error("客户端错误", e);
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) {
            return;
        }

        // 客户端SocketChannel
        SocketChannel socketChannel = (SocketChannel) key.channel();
        if (key.isConnectable()) {
            if (socketChannel.finishConnect()) {
                socketChannel.register(selector, SelectionKey.OP_READ);
                doWrite(socketChannel);
            } else {
                System.exit(1);
            }
        }


        if (key.isReadable()) {
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(readBuffer);
            readBuffer.flip();
            if (readBytes > 0) {
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String req = new String(bytes, StandardCharsets.UTF_8);
                System.out.println("客户端收到服务端消息:" + req);
                doWrite(socketChannel);
            } else if (readBytes < 0) {
                key.cancel();
                socketChannel.close();
            }
        }
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        // 写消息
        String msg = "服务端你好我是:" + System.currentTimeMillis();
        byte[] resp = msg.getBytes(StandardCharsets.UTF_8);
        ByteBuffer writeBuffer = ByteBuffer.allocate(resp.length);
        writeBuffer.put(resp);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
    }
}
