package org.example.auth.filter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 为了使用自定义的 {@link AuthenticationEntryPoint}, 从而自定义发生异常时的响应格式  authenticationManager must be specified
 * @time: 2023/12/6 13:27
 */
@Slf4j
@AllArgsConstructor
public class CustomClientCredentialsTokenEndpointFilter extends ClientCredentialsTokenEndpointFilter {
    private final AuthorizationServerSecurityConfigurer configurer;
    private AuthenticationEntryPoint authenticationEntryPoint;

    public CustomClientCredentialsTokenEndpointFilter(AuthorizationServerSecurityConfigurer configurer) {
        this.configurer = configurer;
    }

    @Override
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        super.setAuthenticationEntryPoint (null);
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected AuthenticationManager getAuthenticationManager() {
        return configurer.and ().getSharedObject (AuthenticationManager.class);
    }

    @Override
    public void afterPropertiesSet() {
        //千万不要加 super.afterPropertiesSet();

        setAuthenticationFailureHandler ((request, response, e) -> authenticationEntryPoint.commence (request, response, e));
        setAuthenticationSuccessHandler ((request, response, authentication) -> {
        });
    }
}
