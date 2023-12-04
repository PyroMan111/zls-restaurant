package com.wnxy.cash.service;

import com.wnxy.cash.dto.DiscountVo;
import com.wnxy.cash.dto.PrePayOrder;
import com.wnxy.cash.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-11-29
 */
public interface IOrderService extends IService<Order> {

    List<PrePayOrder> queryPrePayOrder();

    PrePayOrder getOnePreOrder(Long orderId);

    boolean giveDiscountToOrder(DiscountVo discountVo, Long orderId);
}
