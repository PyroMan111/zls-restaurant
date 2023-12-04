package com.wnxy.kitchen.service;

import com.wnxy.kitchen.dto.DishWaitCookDto;
import com.wnxy.kitchen.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-11-28
 */
public interface IDishService extends IService<Dish> {


    List<DishWaitCookDto> queryDishByTableId(Long tableId);

    List<DishWaitCookDto> queryDishByCategoryId(Long categoryId);
}
