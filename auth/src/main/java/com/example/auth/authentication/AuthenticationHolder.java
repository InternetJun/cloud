package com.example.auth.authentication;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 15:42
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationHolder {
    /** 认证 */
    private Authentication authentication;
    /** 权限 */
    private AuthorizationDecision authorizationDecision;
}
