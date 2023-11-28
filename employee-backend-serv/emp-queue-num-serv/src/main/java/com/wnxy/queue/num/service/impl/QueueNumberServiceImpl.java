package com.wnxy.queue.num.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
     * 叫号：只负责处理传唤的号，并发出消息，每叫一个号发一条消息
     */
    @Override
    public boolean newQueueNumber(QueueNumber queueNumber) {
        boolean res = this.save(queueNumber);

        rabbitTemplate.convertAndSend("queue-num-exchange", "queue-num.key.normal",
                JSONUtil.toJsonStr(queueNumber));

        return res;
    }


}
