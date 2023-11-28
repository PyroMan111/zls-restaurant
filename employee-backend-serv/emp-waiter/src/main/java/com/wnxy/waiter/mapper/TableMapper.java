package com.wnxy.waiter.mapper;

import com.wnxy.waiter.model.dto.TableDto;
import com.wnxy.waiter.model.dto.TableStatusDto;
import com.wnxy.waiter.model.entity.Table;
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
public interface TableMapper extends BaseMapper<Table> {

   List<TableDto> queryAllTable();

   List<TableStatusDto> queryAllTableStatus();

}
