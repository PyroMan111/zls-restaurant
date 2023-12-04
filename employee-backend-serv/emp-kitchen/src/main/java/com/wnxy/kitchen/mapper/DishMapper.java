package com.wnxy.kitchen.mapper;

import com.wnxy.kitchen.dto.DishWaitCookDto;
import com.wnxy.kitchen.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-28
 */
public interface DishMapper extends BaseMapper<Dish> {

    List<DishWaitCookDto> queryDishByTableId(Long tableId);

    List<DishWaitCookDto> queryDishByCategoryId(Long categoryId);

}
