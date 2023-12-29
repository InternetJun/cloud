package org.example.auth.point.client;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.httpEntity.Result;
import org.example.common.core.httpEntity.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: lejun
 * @project: cloud
 * @description: 用于客户端的处理；1，客户端的验证问题；2，权限异常的处理。
 * @time: 2023/12/6 13:30
 */
@Component("customAuthenticationEntryPoint")
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws ServletException {
        Throwable cause = authException.getCause();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        try {
            if (cause instanceof OAuth2AccessDeniedException) {
                // 资源权限不足
                Result resp  = Result.unauthorizd(ResultCode.AUTHORIZED_ERROR);

                response.setHeader("Content-Type", "application/json;charset=utf-8");
                response.getWriter().print(JSON.toJSONString(resp));
                response.getWriter().flush();
            } else if (cause instanceof ClientAuthenticationException) {
                // 未带token或token无效
                // cause == null 一般可能是未带token
                Result resp = Result.failed(ResultCode.CLIENT_OAUTH_FAIL, cause.getMessage());
                response.setHeader("Content-Type", "application/json;charset=utf-8");
                response.getWriter().print(JSON.toJSONString(resp));
                response.getWriter().flush();
            }
        } catch (IOException e) {
            log.error("其他异常error", e);
            throw new RuntimeException(e);
        }
    }

//    private final RequestMatcher authorizationCodeGrantRequestMatcher = new AuthorizationCodeGrantRequestMatcher();

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
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
//        response.setStatus(HttpStatus.OK.value());
//        Result r = Result.unauthorizd("client_id或client_secret错误");
//        response.setHeader("Content-Type", "application/json;charset=utf-8");
//
//        response.getWriter().print(JSON.toJSONString(r));
//        response.getWriter().flush();
//    }
}