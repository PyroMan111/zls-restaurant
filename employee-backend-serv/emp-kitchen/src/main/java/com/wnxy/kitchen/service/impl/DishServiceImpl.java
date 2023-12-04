package com.wnxy.kitchen.service.impl;

import com.wnxy.kitchen.dto.DishWaitCookDto;
import com.wnxy.kitchen.entity.Dish;
import com.wnxy.kitchen.mapper.DishMapper;
import com.wnxy.kitchen.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-28
 */
@Primary
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {


    @Override
    public List<DishWaitCookDto> queryDishByTableId(Long tableId){
        return getBaseMapper().queryDishByTableId(tableId);
    }

    @Override
    public List<DishWaitCookDto> queryDishByCategoryId(Long categoryId){
        return getBaseMapper().queryDishByCategoryId(categoryId);
    }

}
