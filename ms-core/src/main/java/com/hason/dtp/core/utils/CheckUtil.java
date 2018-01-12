package com.hason.dtp.core.utils;

import com.hason.dtp.core.exception.CheckException;
import org.springframework.context.MessageSource;

/**
 * 校验工具
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/6
 */
public class CheckUtil {

    private static MessageSource source;

    public static void setSource(MessageSource source) {
        CheckUtil.source = source;
    }

    public static void notFalse(boolean condition, String key, Object... args) {
        if (!condition) {
            fail(key, args);
        }
    }

    public static void notNull(Object obj, String key, Object... args) {
        if (obj == null) {
            fail(key, args);
        }
    }

    public static void notEmpty(String str, String key, Object... args) {
        if (str == null || str.length() == 0) {
            fail(key, args);
        }
    }

    private static void fail(String key, Object... args) {
        throw new CheckException(source.getMessage(key, args, LocaleUtils.getLocale()));
    }
}
