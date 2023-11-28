package com.wnxy.queue.num.service;

import com.wnxy.queue.num.common.Result;
import com.wnxy.queue.num.entity.QueueNumber;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
public interface IQueueNumberService extends IService<QueueNumber> {


    boolean newQueueNumber(QueueNumber queueNumber, long delayTimes);
}
