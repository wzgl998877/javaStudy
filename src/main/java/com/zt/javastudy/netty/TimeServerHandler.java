
package com.zt.javastudy.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author zhengtao
 * @create 2022/10/30 15:44
 */
@Slf4j
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8).substring(0, req.length
                - System.getProperty("line.separator").length());
        log.info("服务端第{}次收到消息,内容为:{}", ++counter, body);
        String resp = "hello World" + System.currentTimeMillis() + System.getProperty("line.separator");
        ByteBuf respByte = Unpooled.copiedBuffer(resp.getBytes());
        ctx.writeAndFlush(respByte);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }
}
