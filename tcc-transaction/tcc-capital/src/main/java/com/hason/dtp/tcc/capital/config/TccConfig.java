package com.hason.dtp.tcc.capital.config;

import com.hason.dtp.tcc.capital.config.properties.TccDataSourceProperties;
import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.serializer.KryoTransactionSerializer;
import org.mengyun.tcctransaction.serializer.ObjectSerializer;
import org.mengyun.tcctransaction.spring.repository.SpringJdbcTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

/**
 * TCC 事务配置
 *
 * @author Huanghs
 * @since 2.0
 * @date 2017/12/5
 */
@Configuration
@ImportResource(locations = "classpath:tcc-transaction.xml")
public class TccConfig {

    @Autowired
    private TccDataSourceProperties properties;

    @Bean
    public TransactionRepository transactionRepository(
            ObjectSerializer<?> serializer) {

        SpringJdbcTransactionRepository repository = new SpringJdbcTransactionRepository();
        repository.setDataSource(tccDataSource());
        repository.setDomain("CAPITAL");
        repository.setTbSuffix("_CAPITAL");
        repository.setSerializer(serializer);
        return repository;
    }

    // 这里定义 TCC 数据源，避免 JPA 使用了 tcc 的数据源
    public DataSource tccDataSource() {
        return DataSourceBuilder.create()
                .type(properties.getType())
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }

    @Bean
    public ObjectSerializer<?> objectSerializer() {
        return new KryoTransactionSerializer();
    }

}
