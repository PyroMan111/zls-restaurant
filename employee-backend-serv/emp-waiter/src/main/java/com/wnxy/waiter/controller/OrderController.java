package com.wnxy.waiter.controller;


import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.wnxy.waiter.common.Result;
import com.wnxy.waiter.common.enums.impl.BusinessCode;
import com.wnxy.waiter.config.anon.Idempotent;
import com.wnxy.waiter.model.vo.CartVo;
import com.wnxy.waiter.redisConstant.RedisConstant;
import com.wnxy.waiter.service.IOrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.http.Result;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * 下订单
 * @author 作者
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private IOrderService orderService;

    /**
     * 提交订单
     */
    @PostMapping("/submitOrder/{tableId}")
    @Idempotent(expireTime = 30, timeunit = TimeUnit.SECONDS)
    public Result submitOrder(
            @PathVariable("tableId") Long tableId,
            @RequestBody CartVo cartVo) {

        String key = "order_" + tableId;
        // 获取分布式锁对象RLock
        RLock rLock = redissonClient.getLock(key);
        try {
            // 加锁；默认锁的过期时间是30秒；
            // 后台会启动一个看门狗的线程watchdog，每10秒执行一次，给锁进行续期到30秒
            rLock.lock();
            // 执行加锁的业务操作....
            // 创建订单
            orderService.submitOrder( tableId, cartVo);

            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放锁
            rLock.unlock();
        }


        return Result.ok(true);
    }


}

