package com.hason.dtp.tcc.account.config;

import com.hason.dtp.core.utils.converter.BooleanAttributeConverter;
import com.hason.dtp.tcc.account.TccAccountApplication;
import com.hason.dtp.tcc.account.entity.User;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * JPA 实体配置，指定类所在的包及子包都会被扫描（每个包路径只需一个类）
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/10/20
 */
@Configuration
@EntityScan(basePackageClasses = { User.class, TccAccountApplication.class, BooleanAttributeConverter.class})
public class JpaConfig {
}