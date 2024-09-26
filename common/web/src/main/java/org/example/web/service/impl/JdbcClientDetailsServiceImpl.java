package org.example.web.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.web.config.CustomClientDetails;
import org.example.web.dto.ClientDto;
import org.example.web.mapper.ClientMapper;
import org.example.web.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/24 16:16
 */
@Service
@Slf4j
public class JdbcClientDetailsServiceImpl implements ClientDetailsService, ClientRegistrationService {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ClientMapper clientMapper;

    /**
     * 核心方法。加载ClientDetails by clientId
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        log.debug("About to produce ClientDetails with client-id: {}", clientId);

        // 先从缓存中获取 ClientDto
        ClientDto clientDto = null;
        final String cacheKey = redisUtil.get("auth", Map.class).get(clientId).toString();

        clientDto = redisUtil.get(cacheKey, ClientDto.class);
        // 如果缓存中没有, 从数据库查询并置入缓存
        if (Objects.isNull(clientDto)) {
            clientDto = clientMapper.getClient(clientId);

            if (Objects.isNull(clientDto)) {
                throw new ClientRegistrationException(String.format("客户端 %s 尚未注册!", clientId));
            }

            // Buffer: 10s 重写redis的util。可以满足基本的需求。
            redisUtil.set(cacheKey, clientDto, clientDto.getAccessTokenValidity() + 10);
        }

        return new CustomClientDetails(clientDto);
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {

    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {

    }

    @Override
    public void updateClientSecret(String s, String s1) throws NoSuchClientException {

    }

    @Override
    public void removeClientDetails(String s) throws NoSuchClientException {

    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return null;
    }
}
