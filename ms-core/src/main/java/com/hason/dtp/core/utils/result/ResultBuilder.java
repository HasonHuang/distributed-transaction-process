package com.hason.dtp.core.utils.result;

import com.hason.dtp.core.exception.ErrorResult;

/**
 * 结果构造器
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/8
 */
public class ResultBuilder {

    public static <T> Result<T> newInstance() {
        return new Result<>();
    }

    public static <T> Result<T> newInstance(ErrorResult error) {
        Result<T> result = new Result<>();
        result.setError(error);
        return result;
    }

    public static <T> Result<T> newInstance(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public static <T> Result<T> newInstance(T data, ErrorResult error) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setError(error);
        return result;
    }


}
