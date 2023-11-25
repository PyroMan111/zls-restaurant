package com.wnxy.waiter.controller;


import cn.hutool.json.JSONUtil;
import com.wnxy.waiter.model.vo.CartItemDto;
import com.wnxy.waiter.redisConstant.RedisConstant;
import com.wnxy.waiter.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService cartService;
    @Autowired
    private RedisTemplate redisTemplate;

    public CartController() {
    }

    public CartController(ICartService cartService, RedisTemplate redisTemplate) {
        this.cartService = cartService;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 购物车修改数量
     */
//    @PostMapping("/changeCartNum")
//    public ResponseEntity changeCartNum(@RequestBody CartItemDto
//                                        CartItemDto, @RequestHeader("Authorization") String token) {
//// 根据user_cart_userId这个key，加上dishId, 从Redis获取carItemVo的json存储字符串
//        token = token.replace("Bearer ", "");
//        JWT jwt = JWTUtil.parseToken(token);
//        Number userId = (Number) jwt.getPayload("userId");
//// 从redis获取购物项
//        String userKey = RedisConstant.USER_CART_PREFIX + userId.toString();
//        String json = (String) redisTemplate.opsForHash().get(userKey,
//                CartItemDto.getId().toString());
//
//        // json---> CartItemDto
//        com.wnxy.waiter.model.vo.CartItemDto itemVo = JSONUtil.toBean(json, CartItemDto.class);
//// 设置购买数量
//        itemVo.setBuycount(CartItemDto.getBuycount());
//// 重新计算小计 = 单价 * 数量
//        itemVo.setSumPrice(itemVo.getPrice().multiply(new BigDecimal(itemVo.getBuycount()
//        )));
//// 将修改后的对象存入Redis: 用户ID 图书ID CartItemDto的json
//        redisTemplate.opsForHash().put(userKey, itemVo.getId().toString(), JSONUtil.toJsonStr(itemVo));
//        return ResponseEntity.ok();
//    }


    /**
     * 点菜下单的业务细节：
     * 先把所有菜品展示出来，提供一个添加若干菜下单的接口
     * 这个接口是先存入redis，
     *
     *
     * */


    /**
     * 菜品添加到购物车
     * 情况1：客人扫码自动从小程序登录获取到orderId
     * 客人坐好，直接口头跟服务员点餐，这时是服务员的id
     * <p>
     * 随便选些菜，先存入redis，有个五分钟的过期时间，
     * 点选立即下单后，才往order里插入一条记录，
     * 如果不继续点击下一步付款，那么这条记录的状态会在redis中的那个key过期时被设置为作废
     */
    @PostMapping("/add/{ordererId}/{dishId}")
    public ResponseEntity addCart(
            @PathVariable("ordererId") Integer ordererId,
            @PathVariable("dishId") Integer dishId) {
        // 处理token
//        token = token.replace("Bearer ", "");
//        JWT jwt = JWTUtil.parseToken(token);
//        Number userId = (Number) jwt.getPayload("userId");

        // 调用Service，实现菜品添加到购物车的逻辑处理
        cartService.addCart(ordererId, dishId);

//        下达的订单先存入redis中，设置过期时间

        return ResponseEntity.ok("添加到了购物车");
    }

    /**
     * 购物车修改数量
     */
    @PostMapping("/changeCartNum")
    public ResponseEntity changeCartNum(@RequestBody CartItemDto cartItemDto) {
// 根据user_cart_userId这个key，加上bookId, 从Redis获取carItemVo的json存储字符串

//        token = token.replace("Bearer ", "");
//        JWT jwt = JWTUtil.parseToken(token);
//        Number userId = (Number) jwt.getPayload("userId");

        // 从redis获取购物项
        String userKey = RedisConstant.ORDERER_CART_PREFIX + cartItemDto.getOrdererId().toString();
        String json = (String) redisTemplate.opsForHash().get(userKey,
                cartItemDto.getDishId().toString());

        // json---> cartItemDto
        CartItemDto itemVo = JSONUtil.toBean(json, CartItemDto.class);
        // 设置购买数量
        itemVo.setBuycount(cartItemDto.getBuycount());
        // 重新计算小计 = 单价 * 数量
        itemVo.setSumPrice(itemVo.getPrice().multiply(new BigDecimal(itemVo.getBuycount()
        )));
        // 将修改后的对象存入Redis: 用户ID 图书ID CartItemDto的json
        redisTemplate.opsForHash().put(userKey, itemVo.getDishId().toString(), JSONUtil.toJsonStr(itemVo));
        return ResponseEntity.ok(true);
    }

    @PostMapping("/deleteById")
    public ResponseEntity deleteById(@RequestBody CartItemDto cartItemDto) {

//        token = token.replace("Bearer ", "");
//        JWT jwt = JWTUtil.parseToken(token);
//        Number userId = (Number) jwt.getPayload("userId");

        // 删除
        redisTemplate.opsForHash().delete(RedisConstant.ORDERER_CART_PREFIX +
                cartItemDto.getOrdererId(), String.valueOf(cartItemDto.getDishId()));
        return ResponseEntity.ok(true);
    }


    /**
     * 获取
     *
     * @return cartService
     */
    public ICartService getCartService() {
        return cartService;
    }

    /**
     * 设置
     *
     * @param cartService
     */
    public void setCartService(ICartService cartService) {
        this.cartService = cartService;
    }

    /**
     * 获取
     *
     * @return redisTemplate
     */
    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * 设置
     *
     * @param redisTemplate
     */
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String toString() {
        return "CartController{cartService = " + cartService + ", redisTemplate = " + redisTemplate + "}";
    }
}