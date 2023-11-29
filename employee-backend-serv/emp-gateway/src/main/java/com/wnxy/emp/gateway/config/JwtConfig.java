package com.wnxy.emp.gateway.config;

import com.wnxy.common.util.JwtTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public JwtTemplate jwtTemplate() {
        return new JwtTemplate(); // 将其替换为创建Jwt Template对象的实际逻辑
    }
}