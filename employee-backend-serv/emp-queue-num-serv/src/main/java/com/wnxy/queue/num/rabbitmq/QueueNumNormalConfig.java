//package com.wnxy.queue.num.rabbitmq;
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//
//public class QueueNumNormalConfig {
//
//
////    算出来的等待时长，暂定为半小时 Queuing time对应了延时的时间
//
////    过期时间 5分钟，五分钟未就位则投入死信队列，死信队列将自动监听并且执行一个update状态status=1过号的操作
//
//
//    final Integer duration = 30; //分钟
//
//
//    // 普通队列: queue-num-queue
//    // 普通交换机: queue-num-exchange
//    // 队列绑定到交换机：queue-num.key.normal
//    @Bean
//    public Queue queueNumQueue(){
//        return QueueBuilder.durable("order-queue-num-queue") //配置业务队列，绑定到业务交换机上
//                .deadLetterExchange("queue-num-dlx-exchange") //为业务队列配置死信交换机和路由key
//                .deadLetterRoutingKey("queue-num.key.dlx")  //死信路由key
////                .ttl(15 * 60 * 1000) // 消息TTL为15分钟
////                正式项目大概为45min
////                .ttl(15 * 60 * 1000) // 消息TTL为15分钟
//                .ttl(duration * 60 * 1000) // 消息TTL为n分钟
//                .maxLength(30000) // 最大长度
//                .build();
//    }
//    @Bean
//    public DirectExchange queueNumExchange() {
//        DirectExchange normalExchange = new DirectExchange("queue-num-exchange");
//        return normalExchange;
//    }
//    @Bean
//    public Binding bindNormalQueueToExchange(Queue queueNumQueue, DirectExchange queueNumExchange) {
//        return BindingBuilder.bind(queueNumQueue).to(queueNumExchange).with("queue-num.key.normal");
//    }
//
//    // 死信队列: queue-num-dlx-queue
//    // 死信交换机: queue-num-dlx-exchange
//    // 死信队列绑定到交换机：queue-num.key.dlx
//    @Bean
//    public Queue orderDlxQueue(){
//        return new Queue("queue-num-dlx-queue");
//    }
//    @Bean
//    public DirectExchange orderDlxExchange() {
//        return new DirectExchange("queue-num-dlx-exchange");
//    }
//    @Bean
//    public Binding bindDLX(Queue orderDlxQueue, DirectExchange orderDlxExchange) {
//        return BindingBuilder.bind(orderDlxQueue).to(orderDlxExchange).with("queue-num.key.dlx");
//    }
//
//
////    @Bean
////    public Queue timeOutQueueNum(){
////        return QueueBuilder.durable("timeOutQueue").build();
////    }
//}