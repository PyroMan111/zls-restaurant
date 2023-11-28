package com.wnxy.waiter.listener;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.wnxy.waiter.model.vo.CartVo;
import com.wnxy.waiter.redisConstant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "clearBuyCartQueue")
@Slf4j
public class ClearCartLinstener {
    @Autowired
    private RedisTemplate redisTemplate;

    //清理购物车
    @RabbitHandler
    public void handler(

            String str, Channel channel, Message message,
            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
        log.info("ClearCart监听器，监听到的JSON字符串消息：{}", str);
        CartVo cartVo = JSONUtil.toBean(str, CartVo.class);
        log.info("ClearCart监听器，JSON->CartVo,{}", cartVo);
        try{
        // 删除购物车中的购物项
            cartVo.getCartItemDto().forEach(e->{
                redisTemplate.opsForHash().delete(
                        RedisConstant.ORDERER_CART_PREFIX + cartVo.getOrdererId(),""+e.getDishId());
            });
            channel.basicAck(deliveryTag,false);
        }catch (Exception e){
            e.printStackTrace();
            channel.basicNack(deliveryTag,false,true);
        }
    }
}