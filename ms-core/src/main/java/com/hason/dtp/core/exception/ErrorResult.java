package com.hason.dtp.core.exception;

/**
 * API 返回错误信息
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/23
 */
public class ErrorResult {

    /** 状态码 */
    private int code;
    /** 消息 */
    private String message;

    public ErrorResult() {
    }

    public ErrorResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
