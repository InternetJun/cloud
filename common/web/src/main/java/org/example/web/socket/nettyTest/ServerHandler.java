package org.example.web.socket.nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/29 15:44
 */
public class ServerHandler extends ChannelDuplexHandler {
    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String body = new String(bytes, "UTF-8").substring(0, bytes.length - System.getProperty("line.separator").length());

        System.out.println("服务端收到消息内容为：" + body + "，收到消息次数：" + ++counter);
    }
}
