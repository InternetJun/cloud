package org.example.common.core.httpEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
* @Author: lejun
* @project: cloud
* @descript: 统一返回体
* @time: 2023/1/1 21:23
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> implements Serializable {

    private String code;

    private String msg;

    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    public static <T> Result unauthorizd(T data) {
        return new Result(ResultCode.ACCESS_UNAUTHORIZED.getCode(), ResultCode.ACCESS_UNAUTHORIZED.getMsg(), data);
    }

    public static <T> Result<T> failed() {
        return result(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), ResultCode.SYSTEM_EXECUTION_ERROR.getMsg(), null);
    }

    public static <T> Result<T> failed(String msg) {
        return result(ResultCode.SYSTEM_EXECUTION_ERROR.getCode(), msg, null);
    }

    public static <T> Result<T> judge(boolean status) {
        if (status) {
            return success();
        } else {
            return failed();
        }
    }

    public static <T> Result<T> failed(IResultCode resultCode) {
        return result(resultCode.getCode(), resultCode.getMsg(), null);
    }

    public static <T> Result<T> failed(IResultCode resultCode, String msg) {
        return result(resultCode.getCode(), msg, null);
    }

    private static <T> Result<T> result(IResultCode resultCode, T data) {
        return result(resultCode.getCode(), resultCode.getMsg(), data);
    }

    private static <T> Result<T> result(String code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static boolean isSuccess(Result<?> result) {
        return result != null && ResultCode.SUCCESS.getCode().equals(result.getCode());
    }

    public static <T> Result<T> forbidden(T data) {
        return new Result<T>(ResultCode.TOKEN_ACCESS_FORBIDDEN.getCode(), ResultCode.TOKEN_ACCESS_FORBIDDEN.getMsg(), data);
    }
}
