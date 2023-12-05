package com.ang.controller;


import cn.hutool.jwt.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ssang
 * @since 2022-07-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/getUserInfo")
    public Object getUserInfo(Authentication authentication){
        return authentication.getPrincipal();
    }

    @RequestMapping("/getUserJwt")
    public Object getUserJwtToken(HttpServletRequest request){
        //获取请求头的指定内容
        String header = request.getHeader("Authorization");
        //截取，去掉请求头的前6位，获取token
        String token = header.substring(header.indexOf("bearer") + 7);
        //解析Token，获取Claims对象
//        Claims claims = Jwts.parser()
//                .setSigningKey("test".getBytes(StandardCharsets.UTF_8))
//                .parseClaimsJws(token)
//                .getBody();
        return null;
    }
}
