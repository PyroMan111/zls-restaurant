package com.wnxy.waiter;

import com.wnxy.waiter.Timer.TableStatusTask;
import com.wnxy.waiter.service.ITableService;
import com.wnxy.waiter.service.impl.TableServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


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
