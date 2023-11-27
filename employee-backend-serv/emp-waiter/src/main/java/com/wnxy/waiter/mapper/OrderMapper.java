package com.wnxy.waiter.mapper;

import com.wnxy.waiter.model.dto.TablePriceDto;
import com.wnxy.waiter.model.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<TablePriceDto> queryTablePriceList();

    Integer queryOrderMaxId();

}
