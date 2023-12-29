package org.example.auth.base;

import org.bouncycastle.crypto.engines.CamelliaLightEngine;
import org.example.auth.mapper.ClientMapper;
import org.example.auth.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.constant.RedisConstant;
import org.example.common.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

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
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public void run(String... args) throws Exception {
        final String allRoles = roleMapper.getAllRoles();
        redisUtil.set(RedisConstant.RESOURCE_ROLES_MAP, allRoles);
        log.info("所有角色已经初始化...");
        initialClients();
        log.info("所有客户顿数据已经初始化...");
//        log.info("所有角色已经初始化...");

    }

    public void initialClients() {
        final List<String> clientList = clientMapper.getClientList();
//        redisUtil.setMap();
    }
}
