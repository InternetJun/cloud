package org.example.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import org.example.auth.config.CustomClientDetails;
import org.example.auth.dto.ClientDto;
import org.example.auth.mapper.ClientMapper;
import org.example.auth.pojo.Client;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/24 16:16
 */
@Service
@Slf4j
public class JdbcClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 核心方法。加载ClientDetails by clientId
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        log.debug("About to produce ClientDetails with client-id: {}", clientId);

//        // 先从缓存中获取 ClientDto
        ClientDto clientDto = new ClientDto();
        final String cacheKey = redisUtil.get("auth", Map.class).get(clientId).toString();
//
        clientDto = redisUtil.get(cacheKey, ClientDto.class);
        // 如果缓存中没有, 从数据库查询并置入缓存
        if (ObjectUtil.isEmpty(clientDto)) {
            Client clientBase = clientMapper.getClient(clientId);
            BeanUtils.copyProperties(clientBase, clientDto, ClientDto.class);
        }
        if (ObjectUtil.isEmpty(clientDto)) {
            throw new ClientRegistrationException(String.format("客户端 %s 尚未注册!", clientId));
        }
        // 查询客户端的资源和权限。
        final Set<String> authorities = clientMapper.getAuthorize(clientId).stream().map(m -> m.get("authorities").toString())
                .collect(Collectors.toSet());

        final Set<String> resourceIds = clientMapper.getResource(clientId).stream().map(m -> m.get("resourceIds").toString())
                .collect(Collectors.toSet());
        clientDto.setAuthorities(authorities);
        clientDto.setResourceIds(resourceIds);

//            // Buffer: 10s 重写redis的util。可以满足基本的需求。
//            redisUtil.set(cacheKey, clientDto, clientDto.getAccessTokenValidity() + 10);
//        }

        return new CustomClientDetails(clientDto);
    }

//    @Override
//    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
//
//    }
//
//    @Override
//    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
//
//    }
//
//    @Override
//    public void updateClientSecret(String s, String s1) throws NoSuchClientException {
//
//    }
//
//    @Override
//    public void removeClientDetails(String s) throws NoSuchClientException {
//
//    }
//
//    @Override
//    public List<ClientDetails> listClientDetails() {
//        return null;
//    }
}
