package org.example.auth.exception;

import org.springframework.http.HttpStatus;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/29 14:20
 */
public class MethodNotAllowedException extends ExtendOAuth2Exception{

    public MethodNotAllowedException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "method_not_allowed---方法不允许访问";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.METHOD_NOT_ALLOWED.value();
    }

}
