package com.hason.dtp.core.support;

import java.lang.annotation.*;

/**
 * 绑定参数到 Java Bean 中，支持绑定多个 Java Bean
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/7
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MultiRequestBody {
}

