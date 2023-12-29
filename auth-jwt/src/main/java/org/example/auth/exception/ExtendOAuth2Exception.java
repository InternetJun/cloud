package org.example.auth.exception;

import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/29 14:16
 */
public class ExtendOAuth2Exception extends OAuth2Exception {

    @Getter
    private String dataMsg;

    public ExtendOAuth2Exception(String msg) {
        super(msg);
    }

    public ExtendOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }

    public ExtendOAuth2Exception(String msg, String dataMsg) {
        super(msg);
        this.dataMsg = dataMsg;

    }
}
