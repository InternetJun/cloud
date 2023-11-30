package com.example.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.Duration;

/**
 * @Author: lejun
 * @project: cloud
 * @description: security的应用设置，有token的处理问题。
 * @time: 2023/11/20 15:34
 */
@Configuration
@Slf4j
@EnableWebFluxSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Value("${spring.cloud.gateway.session.maxSessions}")
    private int maxSessions;
    @Value("${spring.web.proxy.url:#{null}}")
    private String proxyUrl;
    @Value("${spring.cloud.gateway.session.lockRecord:#{null }}")
    private Integer lockRecord;
    @Value("${spring.cloud.gateway.session.lockedTime:PT10S}")
    private Duration lockedTime;


    @PostConstruct
    public void init() {
        //代理地址处理
        if (proxyUrl == null) {
            logger.info("前端无代理");
            proxyUrl = "";
        } else {
            logger.info("前端代理地址【{}】", proxyUrl);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/rsa/publicKey").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * 登录服务地址
     */
    private final String LOGIN_URL = "/login";
    /**
     * 登录成功地址
     */
    private final String SUCCESS_URL = "/login/success";
    /**
     * 登录失败地址
     */
    private final String FAIL_URL = "/login/fail";
    /**
     * Session失效地址
     */
    private final String INVALID_URL = "/login/invalid";

    /**
     * 小程序登录地址
     */
    private final String WECHAT_URL = "/login/weChatLogin";

    /**
     * 登出地址
     */
    private final String LOGOUT_URL = "/login/logout";

//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
//                                                            LoginProcessor loginProcessor,
//                                                            DefaultUserImpl userDetailsService) {
//        http.authorizeExchange(exchanges -> {
//                    ServerHttpSecurity.AuthorizeExchangeSpec access = exchanges
//                            .pathMatchers(SUCCESS_URL, FAIL_URL, INVALID_URL,
//                                    WECHAT_URL, LOGOUT_URL).permitAll();
//                })
//                // 禁用http默认设置
//                .httpBasic().disable()
//                // 登录设置
//                .formLogin()
//                // 设置认证管理器
//                .authenticationManager(new WebReactiveAuthenticationManager(userDetailsService, loginProcessor))
//                //登录服务地址
//                .loginPage(LOGIN_URL)
//                // 设置认证成功处理器
//                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler(
//                        proxyUrl + SUCCESS_URL))
//                // 设置认证失败处理器
//                .authenticationFailureHandler(new WebRedirectServerAuthenticationFailureHandler(
//                        proxyUrl + FAIL_URL))
//                // 设置无权限时端点
//                .authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint(proxyUrl + INVALID_URL))
//                .and()
//                // 登出设置
//                .logout()
//                // 设置登出处理器
//                .logoutHandler(new WebSessionServerLogoutHandler())
//                // 设置登出成功处理器
//                .logoutSuccessHandler(createRedirectServerLogoutSuccessHandler())
//                .and()
//                // 禁用csrf拦截
//                .csrf().disable()
//                // 启用跨域拦截
//                .cors()
////                .and()
////                .exceptionHandling().accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.FORBIDDEN))
//        ;
//        http.anonymous();
//        return http.build();
//    }

    /**
     * 注入登录处理器 bean
     *
     * @param reactiveStringRedisTemplate 响应式 Redis 模板
     * @param sessionRepository           会话仓储
     * @return 登录处理器 bean
     */
//    @Bean
//    public LoginProcessor loginProcessor(ReactiveStringRedisTemplate reactiveStringRedisTemplate,
//                                         ReactiveRedisSessionRepository sessionRepository) {
//        LoginProcessor loginProcessor = new LoginProcessor(reactiveStringRedisTemplate, sessionRepository);
//        loginProcessor.setMaxSessions(maxSessions);
//        if (lockRecord != null) {
//            loginProcessor.setAllowPasswordErrorRecord(lockRecord);
//        }
//        loginProcessor.setLockedTime(lockedTime);
//        return loginProcessor;
//    }
//
//    /**
//     * Security 跨域处理
//     *
//     * @return 跨域配置对象
//     */
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        // 允许所有头请求
//        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        // 允许所有域请求
//        configuration.setAllowedOrigins(Collections.singletonList("*"));
//        // 允许所有方法请求
//        configuration.setAllowedMethods(Collections.singletonList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        // 允许所有地址请求
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    /**
//     * 定义默认用户实现 Bean
//     *
//     * @return 用户实现
//     */
//    @Bean
//    @ConditionalOnMissingBean
//    public DefaultUserImpl userDetailsService(LoginProperties properties,
//                                              UserMapper userMapper) {
//        return new DefaultUserImpl(properties, userMapper);
//    }
//
//    /**
//     * 创建登出成功跳转处理器
//     *
//     * @return 跳转成功处理器对象
//     */
//    private RedirectServerLogoutSuccessHandler createRedirectServerLogoutSuccessHandler() {
//        RedirectServerLogoutSuccessHandler redirectServerLogoutSuccessHandler = new RedirectServerLogoutSuccessHandler();
//        redirectServerLogoutSuccessHandler.setLogoutSuccessUrl(URI.create(proxyUrl + LOGOUT_URL));
//        return redirectServerLogoutSuccessHandler;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
