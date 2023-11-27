package com.wnxy.waiter.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderDeadLetterConfig {
    // 普通队列: order-queue
    // 普通交换机: order-exchange
    // 队列绑定到交换机：order.key.normal
    @Bean
    public Queue orderQueue(){
        return QueueBuilder.durable("order-queue") //配置业务队列，绑定到业务交换机上
                .deadLetterExchange("order-dlx-exchange") //为业务队列配置死信交换机和路由key
                .deadLetterRoutingKey("order.key.dlx")
//                .ttl(15 * 60 * 1000) // 消息TTL为15分钟
//                正式项目大概为45min
//                .ttl(15 * 60 * 1000) // 消息TTL为15分钟
                .ttl(1 * 60 * 1000) // 消息TTL为2分钟
                .maxLength(30000) // 最大长度
                .build();
    }
    @Bean
    public DirectExchange orderExchange() {
        DirectExchange normalExchange = new DirectExchange("order-exchange");
        return normalExchange;
    }
    @Bean
    public Binding bindNormalQueueToExchange(Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with("order.key.normal");
    }

    // 死信队列: order-dlx-queue
    // 死信交换机: order-dlx-exchange
    // 死信队列绑定到交换机：order.key.dlx
    @Bean
    public Queue orderDlxQueue(){
        return new Queue("order-dlx-queue");
    }
    @Bean
    public DirectExchange orderDlxExchange() {
        return new DirectExchange("order-dlx-exchange");
    }
    @Bean
    public Binding bindDLX(Queue orderDlxQueue, DirectExchange orderDlxExchange) {
        return BindingBuilder.bind(orderDlxQueue).to(orderDlxExchange).with("order.key.dlx");
    }
}