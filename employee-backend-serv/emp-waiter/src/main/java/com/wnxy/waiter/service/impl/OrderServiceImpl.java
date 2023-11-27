package com.wnxy.waiter.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wnxy.waiter.common.constant.OrderStatusEnum;
import com.wnxy.waiter.common.enums.impl.BusinessCode;
import com.wnxy.waiter.common.exception.Asserts;
import com.wnxy.waiter.common.exception.BusinessException;
import com.wnxy.waiter.mapper.OrderDishMapper;
import com.wnxy.waiter.mapper.OrderMapper;
import com.wnxy.waiter.model.dto.CartItemDto;
import com.wnxy.waiter.model.entity.Order;
import com.wnxy.waiter.model.entity.OrderDish;
import com.wnxy.waiter.model.vo.CartVo;
import com.wnxy.waiter.service.IEmployeeService;
import com.wnxy.waiter.service.IOrderService;
import com.wnxy.waiter.service.ITableService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private OrderDishMapper orderDishMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ITableService tableService;
 @Autowired
    private IEmployeeService employeeService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitOrder(Long tableId, CartVo cartVo) {
        // 生产订单ID: 雪花算法生成
        // 参数1 机器id； 参数2：机房id
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long orderId = snowflake.nextId();

        // 保存订单明细
        List<CartItemDto> cartItemDtos = cartVo.getCartItemDto();

        cartItemDtos.forEach(cartItemDto -> {
            /**
             * 用Redisson实现分布式锁，解决超卖问题
             *
             * 做库存的查询，库存要存入redis
             * 校验库存数量
             * 扣除
             * 库存数扣完后要释放锁
             * */
//            【获取分布式锁对象】
            RLock rLock = redissonClient.getLock("Lock_" + cartItemDto.getDishId());
            try {
//                【加锁】
                rLock.lock();
                // 库存是否充足？
                Integer storeCount = (Integer) redisTemplate.opsForHash().get("inventory", cartItemDto.getDishId() + "");
                Asserts.error(cartItemDto.getBuycount() > storeCount, BusinessCode.STORAGE_IS_NOT_ENOUGH);
                // 库存充足，扣减库存
                redisTemplate.opsForHash().
                        increment("inventory", cartItemDto.getDishId() + "", -cartItemDto.getBuycount());
            } catch (Exception e) {
                throw new BusinessException(BusinessCode.STORAGE_IS_NOT_ENOUGH);
            } finally {
//                【释放锁】
                rLock.unlock();
            }


            // 订单明细
            OrderDish orderDish = new OrderDish();
            orderDish.setOrderId(orderId);
            orderDish.setDishId(cartItemDto.getDishId());
            orderDish.setCount(cartItemDto.getBuycount());
            orderDish.setTotalPrice(cartItemDto.getSumPrice());
            orderDish.setCreateTime(new Date());
            orderDish.setUpdatTime(new Date());
            orderDishMapper.insert(orderDish);

        });

        // 保存订单
        Order order = new Order();
        order.setId(orderId);
        order.setOrderNumber("WNON" + System.currentTimeMillis());

        order.setTotalPrice(cartVo.getTotalPrice());
        order.setTableId(tableId);

//        下单的人必须是已登录状态，这个下单人可以是顾客，也可以是员工
//        这里只考虑登录状态下的员工，所以通过下单人的id就是emp_id, 进行一次手机号查询



//        根据下单人的ordererId查手机号，这里是以服务员登录，为某一桌下单的视角
        String phone = employeeService.queryTel(Long.valueOf(cartVo.getOrdererId()));

        order.setPhone(phone);
//        需要对接
//        根据tableId查在哪个餐厅
        Long restaurantId = tableService.queryRestaurantIdByTable(tableId);
        order.setRestaurantId(restaurantId);


        order.setCreateTime(new Date());
        order.setPayStatus(OrderStatusEnum.NO_PAY.getCode());
        order.setPayTime(null);

        order.setEmployeeId(cartVo.getOrdererId());
//        0是堂食，1是预约，默认堂食
        order.setType(0);

//        优惠与折扣等功能：待开发
        order.setDiscountedStatus(0);
        order.setDiscountedPrice(0);

        this.save(order);

//        后面再优化，用路由模型，新增一笔订单发一条消息

//        购物车中清除掉，已经购买的书籍（MQ->任务）
        rabbitTemplate.convertAndSend("clearBuyCartQueue", JSONUtil.toJsonStr(cartVo));
//


        rabbitTemplate.convertAndSend("order-exchange", "order.key.normal", JSONUtil.toJsonStr(order));


    }
}
