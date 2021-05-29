package com.marlowe.music.commons.result;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: SpringBoot-VUE-Music
 * @description: 结果类
 * @author: Marlowe
 * @create: 2021-05-29 22:27
 **/
@Data
@AllArgsConstructor
public class Result<T> implements Serializable, IResult<T> {

    /**
     * 200:成功   其他：失败
     */
    private String code;
    private String msg;
    private T data;

    public static Result ok() {
        return new Result(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
    }

    public static Result ok(String msg) {
        return new Result(ErrorCode.SUCCESS.getCode(), msg, null);
    }

    public static Result ok(String msg, Object data) {
        return new Result(ErrorCode.SUCCESS.getCode(), msg, data);
    }

    public static Result ok(Object data) {
        return new Result(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }


    public static Result error() {
        return new Result(ErrorCode.ERROR.getCode(), ErrorCode.ERROR.getMessage(), null);
    }

    public static Result error(ErrorCode errorCode) {
        return new Result(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }


    public static Result error(String code, String msg, Object data) {
        return new Result(code, ErrorCode.ERROR.getMessage(), data);
    }

    public static Result error(ErrorCode errorCode, String msg) {
        return new Result(ErrorCode.ERROR.getCode(), msg, null);
    }

    /**
     * 获得信息
     *
     * @return
     */
    @Override
    public String getMsg() {
        return null;
    }

    /**
     * 获得状态码
     *
     * @return
     */
    @Override
    public String getCode() {
        return null;
    }

    /**
     * 获得数据
     *
     * @return
     */
    @Override
    public T getData() {
        return null;
    }
}
