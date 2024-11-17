package com.shanzhu.parking.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis - plus 配置
 *
 * 什么是MybatisPlus? （https://blog.csdn.net/qq_52922453/article/details/127196313）
 *
 * @author: Cheng Zi
 * @date: 2024-09-21
 */
@Configuration
@MapperScan("${mybatis-plus.mapper-package}")
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisPlusConfig {

    /**
     * 分页插件配置
     *
     * @return MybatisPlusInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
