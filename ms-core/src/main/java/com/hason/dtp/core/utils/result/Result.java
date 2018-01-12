package com.hason.dtp.core.utils.result;

import com.hason.dtp.core.exception.ErrorResult;

import java.io.Serializable;

/**
 * 接口返回的结果
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/8
 */
public class Result<T> implements Serializable {

    /** 数据 */
    private T data;

    /** 错误消息 */
    private ErrorResult error;

    public boolean success() {
        return error == null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorResult getError() {
        return error;
    }

    public void setError(ErrorResult error) {
        this.error = error;
    }
}
