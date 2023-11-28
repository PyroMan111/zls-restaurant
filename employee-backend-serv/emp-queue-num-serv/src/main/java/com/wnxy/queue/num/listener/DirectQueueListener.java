package com.wnxy.queue.num.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "direct-queue2")
public class DirectQueueListener {
    @RabbitHandler
    public void handlerMsg(String msg) {
        log.info("DirectQueueListener2 消费者监听消息:{}", msg);
    }
}
