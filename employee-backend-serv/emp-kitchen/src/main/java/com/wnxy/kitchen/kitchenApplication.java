package com.wnxy.kitchen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *
 *  订单服务，负责管理订单。
 *  创建订单时，需要调用account-service和storage-service
 *     account-service (扣款)
 *     storage-service (库存减少)
 */
@MapperScan("com.wnxy.kitchen")
//@EnableFeignClients
@SpringBootApplication

public class kitchenApplication {
    public static void main(String[] args) {
        SpringApplication.run(kitchenApplication.class, args);
    }
}
