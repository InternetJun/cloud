package com.example.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 授权的应用
 * @time: 2023/11/22 17:02
 */
@SpringBootApplication(scanBasePackages = {"org.example.web.**"})
@EnableDiscoveryClient
@MapperScan(basePackages="org.example.web.mapper")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
