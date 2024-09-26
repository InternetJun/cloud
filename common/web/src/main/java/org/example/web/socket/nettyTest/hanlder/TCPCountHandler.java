package org.example.web.socket.nettyTest.hanlder;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/29 16:20
 */
@ChannelHandler.Sharable
public class TCPCountHandler extends ChannelInboundHandlerAdapter {

    //使用原子类，避免线程安全问题
    private AtomicInteger atomicInteger = new AtomicInteger();

    public TCPCountHandler() {
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            System.out.println("当前连接数为 = " + atomicInteger.get());
        }, 0, 3, TimeUnit.SECONDS);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        atomicInteger.incrementAndGet();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        atomicInteger.decrementAndGet();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("TCPCountHandler exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}
