package org.example.web.filter;

import cn.hutool.core.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/8 11:22
 */
@Component
public class AccessControlFilter implements Filter {
    /**
     * 网关在请求头添加的信息，如果 请求头 有这个值，表示已经经过网关认证，可以访问微服务
     */
    private static final String GATEWAY_TOKEN = "Authorization";
    private static final Logger log = LoggerFactory.getLogger(AccessControlFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取上游请求的请求头
        String header = request.getHeader(GATEWAY_TOKEN);
        log.info("token: " + header);
        //验证上游请求中请求头的token值
        if (ObjectUtil.isEmpty(header)) {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write("请从网关访问微服务");
            return;
        }else {
            filterChain.doFilter(request, response);
            log.info("token校验正确, 请求正常经过网关！");
        }
    }
}