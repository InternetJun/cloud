package org.example.web.service.impl;

import org.example.web.mapper.UserMapper;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 对用户的操作，有权限
 * @time: 2023/11/20 16:36
 */
public class DefaultUserImpl {
//    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

}
