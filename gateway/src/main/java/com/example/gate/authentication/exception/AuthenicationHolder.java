package com.example.gate.authentication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.core.Authentication;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/20 15:42
 */
@AllArgsConstructor
@Getter
@Setter
public class AuthenicationHolder {
    /** 认证 */
    private Authentication authentication;
    /** 权限 */
    private AuthorizationDecision authorizationDecision;
}
