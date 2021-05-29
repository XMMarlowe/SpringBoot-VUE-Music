package com.marlowe.music.commons.result;

/**
 * @program: SpringBoot-VUE-Music
 * @description: 状态码
 * @author: Marlowe
 * @create: 2021-05-29 22:29
 **/
public enum ErrorCode {

    /**
     * 请求成功
     */
    SUCCESS("200", "请求成功"),
    /**
     * 请求失败
     */
    ERROR("500", "请求失败");


    private String code;
    private String message;

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    ErrorCode(String _code, String _message) {
        this.code = _code;
        this.message = _message;
    }
}
