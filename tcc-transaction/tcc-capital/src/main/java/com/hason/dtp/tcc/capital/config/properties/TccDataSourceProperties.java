package com.hason.dtp.tcc.capital.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * TCC 数据源配置属性
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/8
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.tcc")
public class TccDataSourceProperties {

    /**
     * Fully qualified name of the JDBC driver. Auto-detected based on the URL by default.
     */
    private String driverClassName;

    /**
     * JDBC url of the database.
     */
    private String url;

    /**
     * Login user of the database.
     */
    private String username;

    /**
     * Login password of the database.
     */
    private String password;

    /**
     * Fully qualified name of the connection pool implementation to use. By default, it
     * is auto-detected from the classpath.
     */
    private Class<? extends DataSource> type;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Class<? extends DataSource> getType() {
        return type;
    }

    public void setType(Class<? extends DataSource> type) {
        this.type = type;
    }
}
