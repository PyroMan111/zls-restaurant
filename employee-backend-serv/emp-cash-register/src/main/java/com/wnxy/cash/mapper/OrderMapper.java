package com.wnxy.cash.mapper;

import com.wnxy.cash.dto.PrePayOrder;
import com.wnxy.cash.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-29
 */
public interface OrderMapper extends BaseMapper<Order> {

   List<PrePayOrder> queryPrePayOrder();

   PrePayOrder getOnePreOrder(Long orderId);


}
