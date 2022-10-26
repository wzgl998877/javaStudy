package com.zt.javastudy.netty.nio;

import org.apache.commons.collections.EnumerationUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhengtao
 * @create 2022/10/27 0:56
 */
public class NIO1Server implements Runnable {
    private Selector selector;
    private ServerSocketChannel socketChannel;
    private volatile boolean stop;

    public NIO1Server() throws IOException {
        selector = Selector.open();
        socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.socket().bind(new InetSocketAddress("localhost", 8888));
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动了");
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        key.cancel();
                        if (key.channel() != null) {
                            key.channel().close();
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
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
            if (readBytes > 0) {
                readBuffer.flip();
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String req = new String(bytes, StandardCharsets.UTF_8);
                System.out.println("服务端收到客户端消息:" + req);
                // 写消息
                String msg = "客户端你好现在是:" + System.currentTimeMillis();
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

    public static void main(String[] args) throws IOException {
        new Thread(new NIO1Server()).start();
    }
}
