package org.example.web.chapter;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Author: lejun
 * @project: cloud
 * @description: inet的使用
 * @time: 2023/9/27 9:47
 */
public class API_ {
    public static void main(String[] args) throws UnknownHostException {
        // LAPTOP-M9EKQI86/192.168.0.105
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress);

        System.out.println(InetAddress.getByName("LAPTOP-M9EKQI86"));
    }
}
