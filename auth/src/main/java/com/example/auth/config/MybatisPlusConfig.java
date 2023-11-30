package com.example.auth.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/11/29 17:30
 */
@EnableTransactionManagement
@Configuration
@MapperScan("org.example.web.mapper")
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


//    @Bean
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        performanceInterceptor.setMaxTime(1000);  // SQL执行最大时间，超过自动停止运行，单位毫秒
//        performanceInterceptor.setFormat(true);    // SQL是否格式化
//        return performanceInterceptor;
//    }

    /**
     * Mybatis SQL执行效率插件配置
     */
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            // 开启驼峰命名规则
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }

    /**
     * 自定义 Mybatis SqlSessionFactory 配置
     * 自定义数据源（clickhouse，oracle，mysql）
     */
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() throws Exception{
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        // ... 配置数据源、类型别名等其他属性
        //设置mybatis的xml所在位置
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:org/.../mapper/**/*Mapper.xml");
        mybatisPlus.setMapperLocations(resources);
        return mybatisPlus;
    }


}
