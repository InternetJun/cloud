package org.example.auth.service.impl;

import org.example.auth.mapper.RoleMapper;
import org.example.auth.mapper.UserMapper;
import org.example.auth.pojo.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ssang
 * @date 2022-07-06 15:39
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        // 从数据库中查找用户信息
        UserInfo user = new UserInfo();
        user.setUsername(username);
        userInfo = userMapper.selectUser(user);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        userInfo.setRoles(roleMapper.getRolesById(userInfo.getId()));
        log.info("用户信息：{}登录系统", userInfo);
        return userInfo;
    }
}
