package com.yongzh;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description: 配置类
 * @date 2023/1/6 21:26
 */
@ComponentScan("com.yongzh")
@EnableTransactionManagement
@Configuration//保证jdbc和十五管理器的dataSource对象是 同一个
public class AppConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8&useSSL=TRUE&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return  dataSource;
    }

}
