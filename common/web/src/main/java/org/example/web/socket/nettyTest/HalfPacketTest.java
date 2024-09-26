package org.example.web.socket.nettyTest;

import io.netty.bootstrap.ServerBootstrap;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/10/29 15:43
 */
public class HalfPacketTest {
    public static void main(String[] args) {
//        final EventLoopGroup bossGroup = new EventLoopGroup();
        //创建启动引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //加入服务端线程组 todo 这两个要怎么创建。
//        serverBootstrap.group(bossGroup,workGroup)
//                //设置管道
//                .channel(NioServerSocketChannel.class)
//                .option(ChannelOption.SO_BACKLOG,1024)
//                //加入处理器
//                .childHandler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//                        //加入处理器ServerHandler
//                        socketChannel.pipeline().addLast(new ServerHandler());
//                    }
//                });

        System.out.println("Echo服务启动中...");
    }
}
