package com.wnxy.waiter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 *  订单服务，负责管理订单。
 *  创建订单时，需要调用account-service和storage-service
 *     account-service (扣款)
 *     storage-service (库存减少)
 */
@MapperScan("com.wnxy.waiter.mapper")
//@EnableScheduling
@SpringBootApplication
public class WaiterApplication {


    public static void main(String[] args) {
        SpringApplication.run(WaiterApplication.class, args);
    }
}

//@EnableFeignClients
