package org.example.web.socket.nettyTest.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.example.web.socket.nettyTest.config.NettyConfig;
import org.example.web.socket.nettyTest.hanlder.TCPCountHandler;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/29 16:18
 */
public class NettyServer {
    public void run(int beginPort, int endPort) {
        System.out.println("服务端启动中。。");
        //配置服务端线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                //.childOption(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.TCP_NODELAY, true)
                //快速复用端口
                .childOption(ChannelOption.SO_REUSEADDR, true);

        serverBootstrap.childHandler(new TCPCountHandler());

        for (; beginPort < endPort; beginPort++) {
            int port = beginPort;
            serverBootstrap.bind(port).addListener((ChannelFutureListener) future -> {
                System.out.println("服务端成功绑定端口 port = " + port);
            });
        }


    }

    /**
     * 启动入口
     *
     * @param args
     */
    public static void main(String[] args) {
        new NettyServer().run(NettyConfig.BEGIN_PORT, NettyConfig.END_PORT);
    }
}
