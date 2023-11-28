package com.wnxy.queue.num.controller;


import com.wnxy.queue.num.common.Result;
import com.wnxy.queue.num.entity.QueueNumber;
import com.wnxy.queue.num.service.IQueueNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 123
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/queue-number")
public class QueueNumberController {
    @Autowired
    private IQueueNumberService queueNumberService;

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
     */


    /**
     * 假设现在某餐厅爆满，A桌正在吃，B桌刚腾出来，C桌有人坐 正准备点菜，
     *
     * 情景1：小明一行人4人叫了个号，等待时间应该不确定，是从取号那一刻开始算，
     * 它们不知道要等多久，等了50分钟不想等了，但是第60分钟 排到它们了，广播开始叫号，叫了三次，座位腾在那儿5分钟
     * 5分钟过了，就过号(此号过期)，此queueNumber投入死信队列，将状态status设置为1 过号
     *
     * */

    /**
     * 调用别的接口预估时间，需要知道等待时间 x
     */
    @PostMapping("/add")
    public Result add(@RequestBody QueueNumber queueNumber, Long waitingTime) {
//        Result result = queueNumberService.newQueueNumber(queueNumber);
//        等待时长单位为分钟，n分钟为 n * 60 * 1000
        long minutes = waitingTime * 1000;

        boolean res = queueNumberService.newQueueNumber(queueNumber, minutes);
        return Result.ok(res);
    }


}

