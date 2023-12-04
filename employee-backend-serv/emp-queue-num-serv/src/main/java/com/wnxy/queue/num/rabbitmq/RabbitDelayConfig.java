package com.wnxy.queue.num.rabbitmq;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitDelayConfig {

    /**测试，延时5秒，然后10s失效投入死信，投入后修改状态*/

    //过期时间5min
    final long expiration = 1;


    // 配置一个延迟交换机
    @Bean
    public CustomExchange customExchange() {

        Map<String, Object> arguments = new HashMap<>();
        // 设置延迟交换机的类型参数：定向模式（路由模式）
        arguments.put("x-delayed-type", "direct");
        // 参数1：交换机名称；参数2：指定消息类型是延迟消息; 参数3：true表示交换机持久化; 参数  4：当交换机没有队列绑定时候不自动删除
        // 参数5：交换机参数
        return new CustomExchange("delayed-exchange", "x-delayed-message", true, false, arguments);

    }


    // 延迟队列
    @Bean
    public Queue delayedQueue() {
        Queue delayQueue = QueueBuilder.durable("delayed-queue")
                .deadLetterExchange("queue-num-dlx-exchange") // 死信交换机
                .deadLetterRoutingKey("queue-num.key.dlx") // 死信队列路由键
                .ttl((int) (expiration * 60 * 1000)) // 消息TTL为n分钟
                .maxLength(30000).build();
        return delayQueue;
    }


    // 队列绑定到交换机
    @Bean
    public Binding bindingDelayedExchange(CustomExchange customExchange, Queue delayedQueue) {
        return BindingBuilder.bind(delayedQueue)
                .to(customExchange).with("msg.delay").noargs();

    }


    // 死信队列: queue-num-dlx-queue
    // 死信交换机: queue-num-dlx-exchange
    // 死信队列绑定到交换机：queue-num.key.dlx
    // 死信队列

    @Bean
    public Queue deadLetterQueue() {
        return new Queue("queue-num-dlx-queue");
    }

    // 死信交换机
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange("queue-num-dlx-exchange");
    }

    // 死信队列绑定到交换机
    @Bean
    public Binding bindDLX(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with("queue-num.key.dlx");
    }

//    @Bean
//    public Queue timeOutQueueNum(){
//        return QueueBuilder.durable("timeOutQueue").build();
//    }
}

