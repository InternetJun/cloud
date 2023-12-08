package org.example.auth.base;

import org.example.auth.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.constant.RedisConstant;
import org.example.common.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 所有角色进行初始化
 * @time: 2023/12/7 16:27
 */
@Component
@Slf4j
public class RedisInitializer implements CommandLineRunner {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public void run(String... args) throws Exception {
        final String allRoles = roleMapper.getAllRoles();
        redisUtil.set(RedisConstant.RESOURCE_ROLES_MAP, allRoles);
        log.info("所有角色已经初始化...");
    }
}
