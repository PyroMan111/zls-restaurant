package com.wnxy.waiter.service;

import com.wnxy.waiter.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wnxy.waiter.model.vo.CartVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
public interface IOrderService extends IService<Order> {


    @Transactional(rollbackFor = Exception.class)
    void submitOrder(Integer tableId, CartVo cartVo);
}
