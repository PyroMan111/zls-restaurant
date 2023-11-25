package com.wnxy.waiter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.wnxy.waiter.model.entity.Dish;
import com.wnxy.waiter.model.vo.CartItemDto;
import com.wnxy.waiter.redisConstant.RedisConstant;
import com.wnxy.waiter.service.ICartService;
import com.wnxy.waiter.service.IDishDetailService;
import com.wnxy.waiter.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class CartServiceImpl implements ICartService {


    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IDishDetailService dishDetailService;

    @Autowired
    private IDishService dishService;

    @Override
    public void addCart(Integer ordererId, Integer dishId) {
        //1. 先从Redis中获取购物项
        String json = (String)
                redisTemplate.opsForHash().get(RedisConstant.ORDERER_CART_PREFIX + ordererId, dishId.toString());
        //2. 判断：是否为空
        CartItemDto cartItemDto = null;
        if (StringUtils.isEmpty(json)) {
            //2.1 如果从Redis中获取的购物项为空，说明第一次添加菜品到购物车
            Dish dishItem = dishService.getById(dishId);

            cartItemDto = BeanUtil.copyProperties(dishItem, CartItemDto.class);

//            设置Dto的id
            cartItemDto.setDishId(dishItem.getId());

            // 菜品的购买数量，默认是1
            cartItemDto.setBuycount(1);
            // 小计: 第一次购买，金额就是菜品的价格
            cartItemDto.setSumPrice(dishItem.getPrice());
        } else {
            //2.2 如果从Redis中获取的购物项不为空, 修改数量+1、小计修改
            // 从redis中获取的购物项json字符串 ---> CartItemDto
            cartItemDto = JSONUtil.toBean(json, CartItemDto.class);
            // 设置购买数量: +1
            cartItemDto.setBuycount(cartItemDto.getBuycount() + 1);
            // 重新计算小计 = 单价 * 数量
            cartItemDto.setSumPrice(cartItemDto.getPrice().multiply(new
                    BigDecimal(cartItemDto.getBuycount())));
        }
        // 把CartItemDto对象存储到Redis的Hash类型中
        redisTemplate.opsForHash().put(RedisConstant.ORDERER_CART_PREFIX + ordererId,
                dishId.toString(),
                JSONUtil.toJsonStr(cartItemDto)
        );

//        订单的过期时间是5分钟, 下面可能执行不成功

        try {
//            redisTemplate.expire(RedisConstant.ORDERER_CART_PREFIX + ordererId, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
