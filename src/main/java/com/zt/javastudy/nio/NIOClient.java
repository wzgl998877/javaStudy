package com.zt.javastudy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhengtao
 */
public class NIOClient implements Runnable {
    private Selector selector;

    public NIOClient(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key;
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
            if (readBytes > 0) {
                readBuffer.flip();
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
        String msg = "服务端你好现在是:" + System.currentTimeMillis();
        byte[] resp = msg.getBytes(StandardCharsets.UTF_8);
        ByteBuffer writeBuffer = ByteBuffer.allocate(resp.length);
        writeBuffer.put(resp);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
    }

    public static void main(String[] args) throws IOException {
        // 打开客户端管道，设置连接为非阻塞模式
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        // 连接到服务端
        boolean isConnect = socketChannel.connect(new InetSocketAddress("localhost", 8888));

        // 创建Reactor线程，创建多路复用器并启动线程
        Selector selector = Selector.open();
        if (isConnect) {
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        new Thread(new NIOClient(selector)).start();
    }
}