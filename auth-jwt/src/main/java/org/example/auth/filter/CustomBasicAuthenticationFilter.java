//package org.example.auth.filter;
//
//import com.alibaba.fastjson.JSON;
//import lombok.extern.slf4j.Slf4j;
//import org.example.common.core.httpEntity.Result;
//import org.example.common.core.httpEntity.ResultCode;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
//import org.springframework.security.oauth2.provider.ClientDetails;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//import org.springframework.security.oauth2.provider.ClientRegistrationException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.annotation.Resource;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Base64;
//
///**
// * @Author: lejun
// * @project: cloud
// * @description:
// * @time: 2023/12/27 21:50
// */
//@Component
//@Slf4j
//public class CustomBasicAuthenticationFilter extends OncePerRequestFilter {
//    @Resource
//    private ClientDetailsService clientDetailsService;
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        if (!request.getRequestURI().contains("/oauth/token")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String[] clientDetails = this.isHasClientDetails(request);
//        // 客户端信息缺失
//        if (clientDetails == null) {
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//            Result resp = Result.clientFailed(ResultCode.CLIENT_MISS);
//            response.getWriter().write(JSON.toJSONString(resp));
//            return;
//        }
//
//        try {
//            this.handle(request, response, clientDetails, filterChain);
//        } catch (RuntimeException coe) {
//            // 客户端认证失败
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//            Result resp = Result.clientFailed(ResultCode.CLIENT_FAIL);
//
//            response.getWriter().write(JSON.toJSONString(resp));
//        }
//
//    }
//
//    private void handle(HttpServletRequest request, HttpServletResponse response, String[] clientDetails, FilterChain filterChain) throws IOException, ServletException {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        ClientDetails details = null;
//        try {
//            details = this.getClientDetailsService().loadClientByClientId(clientDetails[0]);
//        } catch (ClientRegistrationException e) {
//            log.info("client认证失败，{},{}", e.getMessage(), clientDetails[0]);
//            throw new OAuth2Exception("client_id 或client_secret 不正确");
//        }
//
//        if (details == null) {
//            log.info("client认证失败，{}", clientDetails[0]);
//            throw new OAuth2Exception("client_id或client_secret不正确");
//        }
//
//        UsernamePasswordAuthenticationToken token =
//                new UsernamePasswordAuthenticationToken(details.getClientId(), details.getClientSecret(), details.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(token);
//        filterChain.doFilter(request, response);
//    }
//
//    /**
//     * 判断请求头中是否包含client信息，不包含返回null  Base64编码
//     */
//    private String[] isHasClientDetails(HttpServletRequest request) {
//        String[] params = null;
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header != null) {
//            String basic = header.substring(0, 5);
//            if (basic.toLowerCase().contains("basic")) {
//                String tmp = header.substring(6);
//                String defaultClientDetails = new String(Base64.getDecoder().decode(tmp));
//                String[] clientArrays = defaultClientDetails.split(":");
//
//                if (clientArrays.length != 2) {
//                    return params;
//                } else {
//                    params = clientArrays;
//                }
//            }
//        }
//        String id = request.getParameter("client_id");
//        String secret = request.getParameter("client_secret");
//        if (header == null && id != null) {
//            params = new String[]{id, secret};
//        }
//        return params;
//    }
//
//    public ClientDetailsService getClientDetailsService() {
//        return clientDetailsService;
//    }
//
//    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
//        this.clientDetailsService = clientDetailsService;
//    }
//}
