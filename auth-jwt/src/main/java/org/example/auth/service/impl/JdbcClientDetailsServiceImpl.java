package org.example.auth.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import org.checkerframework.checker.units.qual.A;
import org.example.auth.config.CustomClientDetails;
import org.example.auth.dto.ClientDto;
import org.example.auth.mapper.ClientMapper;
import org.example.auth.pojo.Client;
import lombok.extern.slf4j.Slf4j;
import org.example.auth.service.IClient;
import org.example.auth.vo.client.ClientVo;
import org.example.common.core.constant.AuthConstant;
import org.example.common.core.constant.RedisConstant;
import org.example.common.core.httpEntity.Result;
import org.example.common.core.util.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class JdbcClientDetailsServiceImpl implements ClientDetailsService, IClient {
    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 核心方法。加载ClientDetails by clientId
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        log.debug("About to produce ClientDetails with client-id: {}", clientId);

//        // 先从缓存中获取 ClientDto
        ClientDto clientDto = null;
        String cacheKey = RedisConstant.RESOURCE_CLENTS_LIST;
        final Map map = redisUtil.get(cacheKey, Map.class);
        //
//        if (ObjectUtil.isNotEmpty(map)) {
//            final String cacheKey = map.get(clientId).toString();
//            clientDto = JSONObject.parseObject(cacheKey, ClientDto.class);
//        }
        // 如果缓存中没有, 从数据库查询并置入缓存
        if (ObjectUtil.isEmpty(map)) {
            clientDto = new ClientDto();
            Client clientBase = clientMapper.getClient(clientId);
            BeanUtils.copyProperties(clientBase, clientDto, ClientDto.class);
        }
//        else {
//            final JSONObject clients = JSONObject.parseObject(map.toString());
//            clientDto = clients.getObject(clientId, ClientDto.class);
//        }
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

//            // Buffer: 10s 重写redis的util。可以满足基本的需求。 对查出的数据进行缓存.
        if (ObjectUtil.isEmpty(map)) {

        }
//        redisUtil.set(cacheKey, clientDto, clientDto.getAccessTokenValidity() + 10);
//        }

        // return new User(username, clientSecret, clientDetails.getAuthorities());源码把client和用户一起当作一个用户
        return new CustomClientDetails(clientDto);
    }

    @Override
    public Result<Boolean> addClient(ClientVo clientVo) {
        final Result<Boolean> result = new Result<>();

        // 默认的单位是秒。需要特定的说明
        // ID	CLIENT_SECRET	SCOPE	AUTHORIZED_GRANT_TYPE	REDIRECT_URI
        // ACCESS_TOKEN_VALIDITY	REFRESH_TOKEN_VALIDITY	AUTO_APPROVE	DESCRIPTION
//        final ClientDetails clientDetails = new ClientDetails();
        final Client client = new Client();
        client.setId(clientVo.getClientId());
        client.setClientSecret(passwordEncoder.encode(clientVo.getClientSecret()));
        client.setScope(AuthConstant.AUTHORITY_SCOPE);
        client.setAuthorizedGrantType(AuthConstant.AUTHORITY_METHODS);
        client.setAutoApprove(true);
        client.setRedirectUri("");
        clientMapper.insert(client);
        clientMapper.insertClientAuthorization(client.getId());
        return result;
    }
}
