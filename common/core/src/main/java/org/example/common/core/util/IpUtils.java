//package org.example.common.core.util;
//
//import org.springframework.http.server.reactive.ServerHttpRequest;
//
//import java.util.Objects;
//
///**
// * @Author: lejun
// * @project: cloud
// * @description:
// * @time: 2023/11/21 16:19
// */
//public class IpUtils {
//    /**
//     * 获取真实 IP 地址
//     *
//     * @param request 请求对象
//     * @return ip地址
//     */
//    public static String getIpAddr(ServerHttpRequest request) {
//        //Nginx 使用 x-forwarded-for 请求头存放真实 ip 地址
//        String ip = request.getHeaders().getFirst("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            // Apache Http 代理使用 Proxy-Client-IP 请求头存放真实 ip 地址
//            ip = request.getHeaders().getFirst("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            // WebLogic 代理使用 WL-Proxy-Client-IP 请求头存放真实 ip 地址
//            ip = request.getHeaders().getFirst("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            // 无代理时，直接获取远程地址
//            ip = Objects.requireNonNull(request.getRemoteAddress()).getHostName();
//        }
//        return ip;
//    }
//}
