package org.example.auth.exception;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/29 14:19
 */
public class GrantTypeException extends ExtendOAuth2Exception{

    public GrantTypeException(String msg, Throwable t) {
        super(msg, t);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "不支持当前授权类型！！！";
    }

    @Override
    public int getHttpErrorCode() {
        return 400;
    }

}
