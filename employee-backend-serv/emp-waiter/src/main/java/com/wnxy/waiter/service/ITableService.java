package com.wnxy.waiter.service;

import com.wnxy.waiter.model.dto.TableDto;
import com.wnxy.waiter.model.entity.Table;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
public interface ITableService extends IService<Table> {

    List<TableDto> queryAllTableDto();

    Long queryRestaurantIdByTable(Long tableId);
}
