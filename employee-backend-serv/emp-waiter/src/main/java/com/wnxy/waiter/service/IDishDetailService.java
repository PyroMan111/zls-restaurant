package com.wnxy.waiter.service;

import com.wnxy.waiter.model.dto.DishDto;
import com.wnxy.waiter.model.entity.DishDetail;
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
public interface IDishDetailService extends IService<DishDetail> {

    List<DishDto> queryDishDtoByTypeId(Integer typeId);
}
