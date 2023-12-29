package org.example.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.example.auth.domin.domain.Oauth2TokenDto;
import org.example.common.core.constant.RedisConstant;
import org.example.common.core.httpEntity.Result;
import org.example.common.core.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.Map;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/24 15:35
 */
@RestController
@RequestMapping("/oauth")
@Slf4j
public class AuthController {
    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private RedisUtil redisUtil;
    @Resource
    private RestTemplate restTemplate;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public Result<Oauth2TokenDto> postAccess(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken() == null ? " ": oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();
        redisUtil.set(RedisConstant.AUTH_TOKEN, oAuth2AccessToken.getValue());
        return Result.success(oauth2TokenDto);
    }

    @RequestMapping(value = "/authorization_code", method = RequestMethod.POST)
    public Result authorizationCode(@RequestParam Map<String, String> parameters) {
        Map<String,String> resp = restTemplate.postForObject("https://github.com/login/oauth/access_token", parameters, Map.class);
        log.info("用户{}通过github登录....", resp);
        HttpHeaders httpheaders = new HttpHeaders();
        httpheaders.add("Authorization", "token " + resp.get("access_token"));
        HttpEntity<?> httpEntity = new HttpEntity<>(httpheaders);
        ResponseEntity<Map> exchange = restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, httpEntity, Map.class);
        System.out.println("exchange.getBody() = " + exchange.getBody());
        return Result.success("forward:/index.html");
    }
}
