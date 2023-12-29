package org.example.auth.config.securityoauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.auth.point.client.CustomAuthenticationEntryPoint;
import org.example.common.core.httpEntity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

/**
 * @author ssang
 * @date 2022-07-06 15:35
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 默认的登陆端点
     */
    public static final String DEFAULT_LOGIN_URL = "/login";
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                //放行对于swagger是需要有这个路径的
                .antMatchers("/webjars/**", "/doc.html", "/swagger-resources/**", "/v2/api-docs",
                        "/oauth/token","/login/**","/logout/**", "/rsa/publicKey").permitAll()
                .anyRequest().authenticated()//其他路径拦截
                .and()
                .formLogin().permitAll()
                .successHandler((req, resp, authentication) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            log.info("login success:{}", authentication);
                            out.write(new ObjectMapper().writeValueAsString(Result.success("登录成功")));
                            out.flush();
                            out.close();
                        }
                )
                .permitAll()
                .and()
                .logout()
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(Result.success("注销成功!")));
                    out.flush();
                    out.close();
                })
                .permitAll()
            .and()
                .csrf().disable();//csrf关闭
//        http
//            .authorizeRequests()
//            .antMatchers("/rsa/publicKey", "/oauth/**", "/logout/**","/login/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
                // 使用默认的登录路径
//                .loginPage("/loginMe")
//                .usernameParameter("name")
//                .passwordParameter("pass")
//                .loginProcessingUrl("/login")
//                .permitAll()
//                .successHandler((req, resp, authentication) -> {
//                            resp.setContentType("application/json;charset=utf-8");
//                            PrintWriter out = resp.getWriter();
//                            log.info("login success:{}", authentication);
//                            out.write(new ObjectMapper().writeValueAsString(Result.success("登录成功")));
//                            out.flush();
//                            out.close();
//                        }
//                )
//                .successForwardUrl("/index.html")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutSuccessHandler((req, resp, authentication) -> {
//                    resp.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = resp.getWriter();
//                    out.write(new ObjectMapper().writeValueAsString(Result.success("注销成功!")));
//                    out.flush();
//                    out.close();
//                })
//                .permitAll()
//            .and()
//            .csrf().disable().exceptionHandling()
//                .authenticationEntryPoint((req, resp, authException) -> {
//                            resp.setContentType("application/json;charset=utf-8");
//                            resp.setStatus(401);
//                            PrintWriter out = resp.getWriter();
//                            Result respBean = Result.failed("访问失败!");
//                            if (authException instanceof InsufficientAuthenticationException) {
//                                respBean.setMsg("请求失败，请联系管理员!");
//                            }
//                            out.write(new ObjectMapper().writeValueAsString(respBean));
//                            out.flush();
//                            out.close();
//                        }
//                );
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行swagger
        web.ignoring().antMatchers(HttpMethod.GET,
                "/v2/api-docs/",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html/**",
                // 静态资源放行
                "js/**", "css/**", "images/**");
    }

    //注册解码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //注册AuthenticationManager
    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
