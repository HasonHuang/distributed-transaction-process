package com.hason.dtp.core.utils;

import java.util.Locale;

/**
 * 地区语言工具类
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/6
 */
public class LocaleUtils {

    private static final ThreadLocal<Locale> localeThreadLocal = ThreadLocal.withInitial(() -> Locale.CHINESE);

    public static void setLocale(String locale) {
        setLocale(new Locale(locale));
    }

    public static void setLocale(Locale locale) {
        localeThreadLocal.set(locale);
    }

    public static Locale getLocale() {
        return localeThreadLocal.get();
    }

    public static void clear() {
        localeThreadLocal.remove();
    }

}
