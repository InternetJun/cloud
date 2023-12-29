package org.example.auth.exception;

import org.springframework.http.HttpStatus;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/29 14:21
 */
public class UnauthorizedException extends ExtendOAuth2Exception {

    public UnauthorizedException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "unauthorized---未授权";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }

}
