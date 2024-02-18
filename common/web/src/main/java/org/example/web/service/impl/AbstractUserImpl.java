package org.example.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.example.web.mapper.UserMapper;
import org.example.web.pojo.UserInfo;
import org.example.web.properities.LoginProperties;
import org.example.web.service.IUser;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 抽象类中实现对基本功能的实现
 * @time: 2023/11/20 19:35
 */
public abstract class AbstractUserImpl implements IUser, ReactiveUserDetailsService {
    /**
     * 定义登录配置
     */
    private final LoginProperties properties;
    /**
     * 用户 Mapper
     */
    private final UserMapper userMapper;

    public AbstractUserImpl(LoginProperties properties, UserMapper userMapper) {
        this.properties = properties;
        this.userMapper = userMapper;
    }

    @Override
    public UserInfo customFindByUsername(String username) {
        UserInfo user = new UserInfo();
        user.setUsername(username);
        return userMapper.selectUser(user);
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        UserInfo userInfo = null;
        if (ObjectUtils.nullSafeEquals(properties.getUserInfo().getUsername(), username)) {
            //超级管理员
            userInfo = properties.getUserInfo();
            return Mono.just(userInfo);
        }
        // 从数据库中查找用户信息
        userInfo = customFindByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return Mono.justOrEmpty(userInfo);
    }
}
