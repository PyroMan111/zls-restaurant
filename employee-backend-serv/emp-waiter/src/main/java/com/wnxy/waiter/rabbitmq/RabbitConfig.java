package com.wnxy.waiter.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {



    //创建一个简单队列，用来清理购物车
    @Bean
    public Queue queue(){
        return QueueBuilder.durable("clearBuyCartQueue").build();
    }
}
