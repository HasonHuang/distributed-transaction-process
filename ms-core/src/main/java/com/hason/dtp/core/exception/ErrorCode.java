package com.hason.dtp.core.exception;

/**
 * 错误状态码
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/23
 */
public enum ErrorCode {

    BAD_REQUEST(400, 400), UNAUTHORIZED(401, 401), FORBIDDEN(403, 403), INTERNAL_SERVER_ERROR(500, 500),

    BOOK_STATUS_WRONG(200, 20001), BOOK_OWNERSHIP_WRONG(203, 20301), NO_TOKEN(401, 40101),

    BAD_PARAM(200, 20002);

    private int httpStatus;
    private int code;

    ErrorCode(int httpStatus, int code) {
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public int getCode() {
        return code;
    }
}
