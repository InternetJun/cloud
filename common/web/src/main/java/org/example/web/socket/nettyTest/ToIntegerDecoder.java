package org.example.web.socket.nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/29 15:26
 */
public class ToIntegerDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        out.add(byteBuf.readInt());
    }
}
