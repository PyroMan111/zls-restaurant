//package com.wnxy.queue.num.listener;
//
//import cn.hutool.json.JSONUtil;
//import com.rabbitmq.client.Channel;
//import com.woniuxy.portal.redisConstant.RedisConstant;
//import com.woniuxy.portal.vo.CartVo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.support.AmqpHeaders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//@RabbitListener(queues = "clearBuyCartQueue")
//@Slf4j
//public class TimeoutListener {
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    //清理购物车
//    @RabbitHandler
//    public void handler(
//            String str, Channel channel, Message message,
//            @Header(AmqpHeaders.DELIVERY_TAG) Long deliveryTag) throws IOException {
//        log.info("ClearCart监听器，监听到的JSON字符串消息：{}", str);
//        CartVo cartVo = JSONUtil.toBean(str, CartVo.class);
//        log.info("ClearCart监听器，JSON->CartVo,{}", cartVo);
//        try{
//        // 删除购物车中的购物项
//            cartVo.getCartItemVos().forEach(e->{
//                redisTemplate.opsForHash().delete(
//                        RedisConstant.USER_CART_PREFIX + cartVo.getUserId(),""+e.getId());
//            });
//            channel.basicAck(deliveryTag,false);
//        }catch (Exception e){
//            e.printStackTrace();
//            channel.basicNack(deliveryTag,false,true);
//        }
//    }
//}