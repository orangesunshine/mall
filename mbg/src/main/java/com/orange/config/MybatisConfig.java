package com.orange.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.orange.mapper")
public class MybatisConfig {
}
