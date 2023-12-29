package org.example.auth.config.securityoauth;

import lombok.AllArgsConstructor;
import org.example.auth.exception.Auth2ResponseExceptionTranslator;
import org.example.auth.filter.CustomClientCredentialsTokenEndpointFilter;
import org.example.auth.service.impl.JdbcClientDetailsServiceImpl;
import org.example.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ssang
 * @date 2022-07-06 15:41
 */
@AllArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenEnhancer jwtTokenEnhancer;
    private final JdbcClientDetailsServiceImpl clientDetailsService;
//    private final CustomClientCredentialsTokenEndpointFilter customBasicAuthenticationFilter;
//    private final CustomAuthenticationEntryPoint authenticationEntryPoint;

    /**
     * Authorization Code Grant 的获取授权码的端点
     */
    public static final String OAUTH_AUTHORIZE_ENDPOINT = "/oauth/authorize";

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtTokenEnhancer);
        delegates.add(accessTokenConverter());
        //配置JWT的内容增强器
        enhancerChain.setTokenEnhancers(delegates);
        endpoints.authenticationManager(authenticationManager)
                //配置加载用户信息的服务
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);

        // 配置TokenService参数，设置token默认过期时间
        DefaultTokenServices tokenService = new DefaultTokenServices();
        tokenService.setTokenStore(endpoints.getTokenStore());
        tokenService.setSupportRefreshToken(true);
        tokenService.setClientDetailsService(endpoints.getClientDetailsService());
        tokenService.setTokenEnhancer(endpoints.getTokenEnhancer());
        // token默认过期时间
        tokenService.setAccessTokenValiditySeconds(7200);
        // refresh_token默认过期时间
        tokenService.setRefreshTokenValiditySeconds(86400);

        // 该字段设置设置refresh token是否重复使用,true:reuse;false:no reuse.
        tokenService.setReuseRefreshToken(false);
        endpoints.tokenServices(tokenService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /**@2:oauth重写ClientCredentialsTokenEndpointFilter*/
        // 允许表单认证，同时也可以查询参数，但是开启此配置，就会使用默认的ClientCredentialsTokenEndpointFilter，不利于统一异常消息
        // security.allowFormAuthenticationForClients ();

        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint ();
        authenticationEntryPoint.setTypeName ("Form");
        authenticationEntryPoint.setRealmName ("oauth2/client");
        // 使用自定义的 exceptionTranslator
        /**@1:自己重新定义oauth返回的异常信息提示**/
        authenticationEntryPoint.setExceptionTranslator (new Auth2ResponseExceptionTranslator());

        CustomClientCredentialsTokenEndpointFilter endpointFilter = new CustomClientCredentialsTokenEndpointFilter (security);
        // 必须首先执行这个，再继续接下来的配置
        endpointFilter.afterPropertiesSet ();
        endpointFilter.setAuthenticationEntryPoint (authenticationEntryPoint);

        security.addTokenEndpointAuthenticationFilter (endpointFilter);
        security.authenticationEntryPoint (authenticationEntryPoint)
                .tokenKeyAccess ("isAuthenticated()")
                .checkTokenAccess ("permitAll()");
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setKeyPair(keyPair());
        return jwtAccessTokenConverter;
    }

    @Bean
    public KeyPair keyPair() {
        //从classpath下的证书中获取秘钥对
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "123456".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "123456".toCharArray());
    }

}
