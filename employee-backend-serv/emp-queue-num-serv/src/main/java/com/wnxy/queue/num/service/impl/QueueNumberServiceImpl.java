package com.wnxy.queue.num.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wnxy.queue.num.common.constant.QueueNumStatusEnum;
import com.wnxy.queue.num.entity.QueueNumber;
import com.wnxy.queue.num.mapper.QueueNumberMapper;
import com.wnxy.queue.num.service.IQueueNumberService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
@Service
public class QueueNumberServiceImpl extends ServiceImpl<QueueNumberMapper, QueueNumber> implements IQueueNumberService {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 叫号：往queue_number表新增一条记录，记录内容暂时忽略；
     * 新增一条的同时向order-queue-num-queue队列发送一条消息，这条消息的有效时间半小时
     *
     *
     * 版本2：叫号：先新增一条记录，默认排队时间先模拟为半小时（实际应当由状态处于用餐中的桌的个数算出来），
     * 这个号的排队等待时长已知，当达到0时，服务员开始或设备开始播叫第n号，叫三次后，五分钟内人不就位则作废，这时
     * 过号，也可以由服务员主动取消（主动过号）
     *
     * 取号：？查询并修改；当这条队列的
     *
     * 时长：模拟一下，就先设定十五分钟
     *
     * 注：delayTimes的单位是毫秒
     */



    /**
     * 叫号：只负责处理传唤的号，并发出消息，每叫一个号发一条消息
     */
    @Override
    public boolean newQueueNumber(QueueNumber queueNumber, long delayTimes) {
        queueNumber.setStatus(QueueNumStatusEnum.CREATED_WAITING.getCode());
        boolean res = this.save(queueNumber);

        rabbitTemplate.convertAndSend("delayed-exchange","msg.delay", JSONUtil.toJsonStr(queueNumber), message -> {
            message.getMessageProperties().setHeader("x-delay", delayTimes);
            return message;
        });

//        rabbitTemplate.convertAndSend("queue-num-exchange", "queue-num.key.normal",JSONUtil.toJsonStr(queueNumber));

        return res;
    }


}
