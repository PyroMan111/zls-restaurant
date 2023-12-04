package com.wnxy.queue.num.listener;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.rabbitmq.client.Channel;
//import com.woniuxy.portal.common.constant.QueueNumberStatusEnum;
//import com.woniuxy.portal.entity.Item;
//import com.woniuxy.portal.entity.QueueNumber;
//import com.woniuxy.portal.service.IItemService;
//import com.woniuxy.portal.service.IQueueNumberService;
import com.wnxy.queue.num.common.constant.QueueNumStatusEnum;
//import com.wnxy.queue.num.common.constant.QueueNumberStatusEnum;
import com.wnxy.queue.num.entity.QueueNumber;
import com.wnxy.queue.num.service.IQueueNumberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 监听超时未支付的订单，需要修改订单状态为已取消、且需要回退Redis中库存;
 *
 * 号状态(0 新建等待 1 过号 2 就位 )
 * 监听超时没能就位的号，自动将其设置为过号
 */
@Component
@RabbitListener(queues = "queue-num-dlx-queue")
@Slf4j
public class TimeoutListener {
    @Autowired
    IQueueNumberService queueNumberService;

    @RabbitHandler
    public void handler(
            String str, Channel channel, Message message,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {

        /**找到那条消息中的queue_number中的status，将其修改为1*/


        log.info("TimeoutListener 监听排队队列超时，消息内容： " + str);
        // 当前监听器监听的是死信队列中的消息； 消息的内容是：str， 就是QueueNumber对象的JSON格式数据。
        // 要做的处理：
        // 1. 把str转换为QueueNumber对象
        QueueNumber queueNumber = JSONUtil.toBean(str, QueueNumber.class);

        // 校验：先进行判断，确认这条记录的status=0，是新建等待0（超时被投入死信的），是0 则修改为1（过号）

        // 2.1 根据这条记录的id查询
        QueueNumber dbQueueNumber = queueNumberService.getById(queueNumber.getId());

        // 2.2 判断号的状态：号状态(0 新建等待 1 过号 2 就位 )
        if (dbQueueNumber.getStatus().equals(QueueNumStatusEnum.CREATED_WAITING.getCode())) {


            //3. 超时未就位：修改此号状态状态为1，即 已取消
            dbQueueNumber.setStatus(QueueNumStatusEnum.EXPIRED.getCode());
            queueNumberService.updateById(dbQueueNumber);

        }
        // 手动应答
        channel.basicAck(deliveryTag, false);
    }
}