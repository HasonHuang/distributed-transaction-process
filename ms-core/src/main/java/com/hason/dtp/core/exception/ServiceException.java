package com.hason.dtp.core.exception;

/**
 * 业务异常
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/23
 */
public class ServiceException extends RuntimeException {

    private ErrorCode code;

    public ServiceException(String message, ErrorCode code) {
        super(message);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
