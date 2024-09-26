package org.example.web.socket.chapter03;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Author: lejun
 * @project: cloud
 * @description: udp的单播、广播、多播
 * @time: 2023/9/28 10:23
 */
@Slf4j
public class UDPTest {
    public static void main(String[] args) throws IOException {
        // 开启
        final DatagramSocket datagramSocket = new DatagramSocket(2000);
        byte[] buf = new byte[1024];
        final DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
        datagramSocket.receive(receivePack);

        //
        final String ip = receivePack.getAddress().getHostAddress();
        final int port = receivePack.getPort();
        final int length = receivePack.getLength();

    }

    /**
    * udp
     * 单播:点对点
     * 广播：一对多
     * 多播：组播（多组的概念）
     *
     * 例子1：
     * 广播地址：
     * 192.168.124.7
     * mask:
     * 255.255.255.0
     *
     * 网络地址：
     * 192.168.124.0
     * 广播地址：
     * 192.168.124.255
     *
     *
     * <p>
     *     例子2：
     * 广播地址：
     * 192.168.124.7
     * mask:
     * 255.255.255.192
     * 网络地址：
     * 192.168.124.0
     * 广播地址：
     * 192.168.124.255
     * </p>
    * */
}
