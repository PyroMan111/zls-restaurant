package com.wnxy.waiter.service.impl;

import com.wnxy.waiter.entity.Order;
import com.wnxy.waiter.mapper.OrderMapper;
import com.wnxy.waiter.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
