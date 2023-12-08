package com.example.gate.authorization;

import cn.hutool.core.convert.Convert;
import org.example.common.core.constant.AuthConstant;
import org.example.common.core.constant.RedisConstant;
import org.example.common.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 鉴权管理器。
 * @time: 2023/11/24 11:33
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        //从Redis中获取当前路径可访问角色列表
//        URI uri = authorizationContext.getExchange().getRequest().getURI();
        Object obj = redisUtil.get(RedisConstant.RESOURCE_ROLES_MAP);
        List<String> authorities = Convert.toList(String.class,obj);
//        authorities = authorities.stream().collect(Collectors.toList());
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }
}
