package org.example.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/7 15:38
 */
@WebFilter(urlPatterns = "/*",filterName = "sqlFilter")
@Configuration
@Slf4j
public class SqlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    /**
     * @description sql注入过滤
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest request = servletRequest;
        ServletResponse response = servletResponse;
        // 获得所有请求参数名
        Enumeration<String> names = request.getParameterNames();
        String sql = "";
        while (names.hasMoreElements()){
            // 得到参数名
            String name = names.nextElement().toString();
            // 得到参数对应值
            String[] values = request.getParameterValues(name);
            for (int i = 0; i < values.length; i++) {
                sql += values[i];
            }
        }
        if (sqlValidate(sql)) {
            //TODO 这里直接抛异常处理，前后端交互项目中，请把错误信息按前后端"数据返回的VO"对象进行封装
            log.warn("捕获到异常的sql语句:{}", sql);
            throw new IOException("您发送请求中的参数中含有非法字符");
        } else {
            filterChain.doFilter(request,response);
        }
    }

    /**
     * @description 匹配效验
     */
    protected static boolean sqlValidate(String str){
        // 统一转为小写
        String s = str.toLowerCase();
        // 过滤掉的sql关键字，特殊字符前面需要加\\进行转义
        String badStr =
                "select|update|and|or|delete|insert|truncate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute|table|"+
                        "char|declare|sitename|xp_cmdshell|like|from|grant|use|group_concat|column_name|" +
                        "information_schema.columns|table_schema|union|where|order|by|" +
                        "'\\*|\\;|\\-|\\--|\\+|\\,|\\//|\\/|\\%|\\#";
        //使用正则表达式进行匹配
        boolean matches = s.matches(badStr);
        return matches;
    }

    @Override
    public void destroy() {}
}
