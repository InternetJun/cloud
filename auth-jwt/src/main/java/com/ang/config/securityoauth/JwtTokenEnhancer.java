package com.ang.config.securityoauth;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhoukun
 * @Date 2021/2/17 19:56
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication oAuth2Authentication) {
        //自定义的内容存到map中
        Map<String,Object> map = new HashMap<>();
        map.put("sex","男");
        map.put("age",20);
        map.put("name","张三");
        //下转型
        if(accessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken)accessToken;
            defaultOAuth2AccessToken.setAdditionalInformation(map);
            return defaultOAuth2AccessToken;
        }
        return null;
    }
}


