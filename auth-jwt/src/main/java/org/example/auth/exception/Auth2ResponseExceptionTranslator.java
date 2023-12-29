package org.example.auth.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.httpEntity.Result;
import org.example.common.core.httpEntity.ResultCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.DefaultThrowableAnalyzer;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InsufficientScopeException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.util.ThrowableAnalyzer;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.io.IOException;
import java.util.Map;


/**
 * @Author: lejun
 * @project: cloud
 * @description: @oauth的异常处理。验证密码，token的校验，权限的访问，
 * @time: 2023/12/29 14:14
 */
@Slf4j
@Component
@JsonSerialize(using = ExtendOAuth2ExceptionSerializer.class)
public class Auth2ResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    private final ThrowableAnalyzer throwableAnalyzer = new DefaultThrowableAnalyzer ();

    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public ResponseEntity translate(Exception e) throws Exception {

        ResultCode errorCode;

        Throwable[] causeChain = throwableAnalyzer.determineCauseChain (e);

        Exception ase = (OAuth2Exception) throwableAnalyzer.getFirstThrowableOfType (OAuth2Exception.class, causeChain);
        if (ase != null) {
            errorCode = convertOAuthExceptionToErrorCode ((OAuth2Exception) ase);
            return handleOAuth2Exception ((OAuth2Exception) ase, errorCode);
        }

        ase = (AuthenticationException) throwableAnalyzer.getFirstThrowableOfType (AuthenticationException.class, causeChain);
        if (ase != null) {
            errorCode = ResultCode.ACCESS_UNAUTHORIZED;
            return handleOAuth2Exception (new Auth2ResponseExceptionTranslator.UnauthorizedException (e.getMessage (), e), errorCode);
        }

        ase = (AccessDeniedException) throwableAnalyzer.getFirstThrowableOfType (AccessDeniedException.class, causeChain);
        if (ase != null) {
            errorCode = ResultCode.INVALID_TOKEN;
            return handleOAuth2Exception (new Auth2ResponseExceptionTranslator.ForbiddenException (ase.getMessage (), ase), errorCode);
        }


        ase = (HttpRequestMethodNotSupportedException) throwableAnalyzer.getFirstThrowableOfType (
                HttpRequestMethodNotSupportedException.class, causeChain);
        if (ase != null) {
            errorCode = ResultCode.USERNAME_OR_PASSWORD_ERROR;
            return handleOAuth2Exception (new Auth2ResponseExceptionTranslator.MethodNotAllowed (ase.getMessage (), ase), errorCode);
        }

        errorCode = ResultCode.UNKNOWN_ERROR;
        return handleOAuth2Exception (
                new Auth2ResponseExceptionTranslator
                        .ServerErrorException (HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase (), e), errorCode);
    }

    private ResponseEntity<?> handleOAuth2Exception(OAuth2Exception e, ResultCode errorCode) throws IOException {

        HttpHeaders headers = new HttpHeaders ();
        headers.set ("Cache-Control", "no-store");
        headers.set ("Pragma", "no-cache");

        int status = e.getHttpErrorCode ();
        if (status == HttpStatus.UNAUTHORIZED.value () || (e instanceof InsufficientScopeException)) {
            headers.set ("WWW-Authenticate", String.format ("%s %s", OAuth2AccessToken.BEARER_TYPE, e.getSummary ()));
        }

        Result<Map<String, String>> r = Result.failed(e.getAdditionalInformation());

        return new ResponseEntity<> (r, headers,
                HttpStatus.valueOf (status));
    }

    ResultCode convertOAuthExceptionToErrorCode(OAuth2Exception e) {
        ResultCode errorCode;

        String oAuth2ErrorCode = e.getOAuth2ErrorCode ();

        switch (oAuth2ErrorCode) {
            case OAuth2Exception.INVALID_REQUEST:
                errorCode = ResultCode.CLIENT_MISS;
                break;

            case OAuth2Exception.INVALID_CLIENT:
                errorCode = ResultCode.CLIENT_FAIL;
                break;

            case OAuth2Exception.INVALID_GRANT:
            case OAuth2Exception.INSUFFICIENT_SCOPE:
            case OAuth2Exception.INVALID_SCOPE:
            case OAuth2Exception.UNSUPPORTED_GRANT_TYPE:
            case OAuth2Exception.UNAUTHORIZED_CLIENT:
            case OAuth2Exception.REDIRECT_URI_MISMATCH:
            case OAuth2Exception.UNSUPPORTED_RESPONSE_TYPE:
                errorCode = ResultCode.USERNAME_OR_PASSWORD_ERROR;
                break;

            case OAuth2Exception.INVALID_TOKEN:
                errorCode = ResultCode.INVALID_TOKEN;
                break;

            case OAuth2Exception.ACCESS_DENIED:
                errorCode = ResultCode.ACCESS_UNAUTHORIZED;
                break;

            default:
                errorCode = ResultCode.UNKNOWN_ERROR;
                break;
        }

        return errorCode;
    }


    @SuppressWarnings("serial")
    private static class ForbiddenException extends OAuth2Exception {

        public ForbiddenException(String msg, Throwable t) {
            super (msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "access_denied";
        }

        @Override
        public int getHttpErrorCode() {
            return 403;
        }

    }

    @SuppressWarnings("serial")
    private static class ServerErrorException extends OAuth2Exception {

        public ServerErrorException(String msg, Throwable t) {
            super (msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "server_error";
        }

        @Override
        public int getHttpErrorCode() {
            return 500;
        }

    }

    @SuppressWarnings("serial")
    private static class UnauthorizedException extends OAuth2Exception {

        public UnauthorizedException(String msg, Throwable t) {
            super (msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "unauthorized";
        }

        @Override
        public int getHttpErrorCode() {
            return 401;
        }

    }

    @SuppressWarnings("serial")
    private static class MethodNotAllowed extends OAuth2Exception {

        public MethodNotAllowed(String msg, Throwable t) {
            super (msg, t);
        }

        @Override
        public String getOAuth2ErrorCode() {
            return "method_not_allowed";
        }

        @Override
        public int getHttpErrorCode() {
            return 405;
        }

    }
}

