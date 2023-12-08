//package com.ang.point.client;
//
//import cn.hutool.core.util.ObjectUtil;
//import com.ang.config.securityoauth.AuthorizationServerConfig;
//import com.ang.config.securityoauth.SecurityConfig;
//import com.ang.response.ResponseWrapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @Author: lejun
// * @project: cloud
// * @description: 用于客户端的处理
// * @time: 2023/12/6 13:30
// */
//@Component("customAuthenticationEntryPoint")
//@Slf4j
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//
//    private final RequestMatcher authorizationCodeGrantRequestMatcher = new AuthorizationCodeGrantRequestMatcher();
//
//    private final AuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint(SecurityConfig.DEFAULT_LOGIN_URL);
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        log.debug("Custom AuthenticationEntryPoint triggered with exception: {}.", authException.getClass().getCanonicalName());
//
//        // 触发重定向到登陆页面
//        if (authorizationCodeGrantRequestMatcher.matches(request)) {
//            // 定向去登录。
//            loginUrlAuthenticationEntryPoint.commence(request, response, authException);
//            return;
//        }
//
//        ResponseWrapper.forbiddenResponse(response, authException.getMessage());
//    }
//
//    private static class AuthorizationCodeGrantRequestMatcher implements RequestMatcher {
//
//        /**
//         * <ol>
//         *     <li>授权码模式 URI</li>
//         *     <li>隐式授权模式 URI</li>
//         * </ol>
//         */
//        private static final Set<String> SUPPORT_URIS = new HashSet<>(Arrays.asList("response_type=code", "response_type=token"));
//
//        @Override
//        public boolean matches(HttpServletRequest request) {
//
//            if (ObjectUtil.equals(request.getServletPath(), AuthorizationServerConfig.OAUTH_AUTHORIZE_ENDPOINT)) {
//                final String queryString = request.getQueryString();
//                return SUPPORT_URIS.stream().anyMatch(supportUri -> queryString.contains(supportUri));
//            }
//
//            return false;
//        }
//    }
//}