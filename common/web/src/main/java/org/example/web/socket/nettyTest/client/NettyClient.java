package org.example.web.socket.nettyTest.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.web.socket.nettyTest.config.NettyConfig;

import java.util.concurrent.ExecutionException;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/29 16:23
 */
public class NettyClient {
    public void run(int beginPort, int endPort) {
        System.out.println("客户端启动中。。");

        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                //快速复用端口
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                    }
                });

        int index = 0;

        while (true) {
            int finalPort = beginPort + index;
            try {
                bootstrap.connect(NettyConfig.SERVER_ADDR, finalPort).addListener((ChannelFutureListener) future -> {
                    if (!future.isSuccess()) {
                        System.out.println("创建连接失败 port = " + finalPort);
                    }
                }).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            ++index;
            if (index == (endPort - beginPort)) {
                index = 0;
            }
        }

    }

    /**
     * 启动入口
     *
     * @param args
     */
    public static void main(String[] args) {
        new NettyClient().run(NettyConfig.BEGIN_PORT, NettyConfig.END_PORT);
    }
}
