package org.example.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zaxxer.hikari.util.UtilityElf;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.example.web.mapper.UserMapper;
import org.example.web.pojo.UserInfo;
import org.example.web.properities.LoginProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 对用户的操作，有权限
 * @time: 2023/11/20 16:36
 */
public class DefaultUserImpl extends AbstractUserImpl implements UserDetailsService {
    private final UserMapper userMapper;

    /**
     * 实例化密码编码器
     */
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public DefaultUserImpl(LoginProperties properties, UserMapper userMapper) {
        super(properties, userMapper);
        this.userMapper = userMapper;
    }

    @Override
    public boolean lockUser(String username) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccountNonLocked(false);
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username", username);
        // 根据用户名锁定用户
        int update = userMapper.update(userInfo, updateWrapper);
        return update > 0;
    }

    @Override
    public void unLockUser(String username, Duration time) {
        ScheduledThreadPoolExecutor executor =
                new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory(DefaultUserImpl.class));
        // 延迟执行任务
        executor.schedule(() -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setAccountNonLocked(true);
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("username", username);
            // 根据用户名锁定用户
            userMapper.update(userInfo, updateWrapper);
            // 关闭线程池
            executor.shutdown();
        }, time.getSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public boolean updatePassword(String username, String oldPassword, String newPassword) {
        final UserInfo userInfo = customFindByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        boolean match = passwordEncoder.matches(oldPassword, userInfo.getPassword());
        if (match) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set("password", encodedPassword);
            updateWrapper.eq("username", username);
            int update = userMapper.update(null, updateWrapper);
            return update > 0;
        }
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        // 从数据库中查找用户信息
        userInfo = customFindByUsername(username);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return userInfo;
    }
}
