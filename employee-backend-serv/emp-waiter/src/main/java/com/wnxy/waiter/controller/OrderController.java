package com.wnxy.waiter.controller;


import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.wnxy.waiter.config.anon.Idempotent;
import com.wnxy.waiter.model.vo.CartVo;
import com.wnxy.waiter.service.IOrderService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
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
    public ResponseEntity submitOrder(
            @PathVariable("tableId") Integer tableId,
//            @PathVariable("orderNum") String orderNum,
//            @PathVariable("ordererId") String ordererId,
            @RequestBody CartVo cartVo) {

        //完成订单的幂等检查
//        String key = RedisConstant.ORDER_IDEMPOTENT_PREFIX + orderNum;
//        if (redisTemplate.hasKey(key)) {
//            return ResponseEntity.fail(BusinessCode.ORDER_REPEAT_SUBMIT);
//        }
//        //首页提交(非重复提交)，把Key存到Redis中，实现接口幂等性保证
//        redisTemplate.opsForValue().set(key, 1, Duration.ofSeconds(30));

        // 从token中获取用户id
//        token = token.replace("Bearer ", "");
//        JWT jwt = JWTUtil.parseToken(token);
//        Number userId = (Number) jwt.getPayload("userId");

//        Integer ordererId = cartVo.getOrdererId();

        // 创建订单
        orderService.submitOrder( tableId, cartVo);
        return ResponseEntity.ok(true);
    }


}

