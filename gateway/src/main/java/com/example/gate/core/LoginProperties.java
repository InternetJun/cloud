package com.example.gate.core;

import org.example.web.pojo.UserInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 登录的属性
 * @time: 2023/11/20 17:23
 */
@Configuration
@ConfigurationProperties("login")
public class LoginProperties {
    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
