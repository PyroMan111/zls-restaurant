package com.wnxy.emp.gateway;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmpGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpGatewayApplication.class);
    }

    // 网关中添加负载均衡配置，nacos上的服务实例权重配置才生效
//    @Bean
//    public IRule loadbalanceRule() {
//        return new NacosRule();
//    }
}