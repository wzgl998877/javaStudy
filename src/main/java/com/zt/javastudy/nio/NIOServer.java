package com.zt.javastudy.nio;

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
 * @author zhengtao on 2022/10/27
 */
public class NIOServer implements Runnable {
    public NIOServer(Selector selector) {
        this.selector = selector;
    }

    private Selector selector;

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
            // 与客户端建立连接
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
        // 打开服务端管道，用于监听客户端的连接，它是所有客户端连接的父管道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        // 绑定监听端口，设置连接为非阻塞模式
        socketChannel.socket().bind(new InetSocketAddress("localhost", 8888));
        socketChannel.configureBlocking(false);

        // 创建Reactor线程，创建多路复用器并启动线程
        Selector selector = Selector.open();
        new Thread(new NIOServer(selector)).start();

        // 将服务端channel注册到多路复用器上，并监听连接事件
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动了");
    }
}
