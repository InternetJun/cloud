package org.example.web.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/9/28 10:08
 */
@Slf4j
public class TCPTest {
    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(2000);
        System.out.println(serverSocket.getInetAddress() + " Port: " + serverSocket.getLocalPort());

        // 等待客户端的连接
        final Socket accept = serverSocket.accept();
        // TODO: 2023/9/28
        serverSocket.close();
        System.out.println("服务端已经关闭了");

    }

    public static class ClientHolder extends Thread {

        @Override
        public void run() {
            super.run();
            System.out.println("客户端连接");

            try {

            } catch (Exception e) {
                log.info("客户端失败。");
            } finally {
                // 关闭资源
            }
        }
    }
}
