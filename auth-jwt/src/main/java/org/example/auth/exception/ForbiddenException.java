package org.example.auth.exception;

import org.springframework.http.HttpStatus;


public class ForbiddenException extends ExtendOAuth2Exception {

    public ForbiddenException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "access_denied---拒绝访问";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.FORBIDDEN.value();
    }

}
