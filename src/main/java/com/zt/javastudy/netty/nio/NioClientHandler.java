//package com.zt.javastudy.netty.nio;
//
//import io.netty.channel.ChannelHandler;
//
//import java.io.IOException;
//import java.nio.channels.Selector;
//import java.nio.charset.Charset;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class NioClientHandler extends ChannelAdapter {
//
//    public NioClientHandler(Selector selector, Charset charset) {
//        super(selector, charset);
//    }
//
//    @Override
//    public void channelActive(ChannelHandler ctx) {
//        try {
//            System.out.println("链接报告LocalAddress:" + ctx.channel().getLocalAddress());
//            ctx.writeAndFlush("hi! 我是bugstack虫洞栈 BioClient to msg for you \r\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void channelRead(ChannelHandler ctx, Object msg) {
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
//        ctx.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
//    }
//
//}