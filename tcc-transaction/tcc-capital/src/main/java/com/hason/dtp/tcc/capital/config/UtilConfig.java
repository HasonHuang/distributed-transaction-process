package com.hason.dtp.tcc.capital.config;

import com.hason.dtp.core.utils.CheckUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 初始化工具
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/11/6
 */
@Configuration
public class UtilConfig {

    @Bean
    public Boolean methodInvokingFactoryBean(MessageSource source) {
        CheckUtil.setSource(source);
        return Boolean.TRUE;
    }

}
