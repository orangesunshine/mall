package com.orange.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置文件：放在mbg模块下，导致mapper接口autoWired报错
 */
@Configuration
@MapperScan({"com.orange.mapper","com.orange.dao"})
public class MybatisConfig {
}
