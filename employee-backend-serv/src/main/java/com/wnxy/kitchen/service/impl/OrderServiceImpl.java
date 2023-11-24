package com.wnxy.kitchen.service.impl;

import com.wnxy.common.entity.Order;
import com.wnxy.kitchen.mapper.OrderMapper;
import com.wnxy.kitchen.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-23
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
