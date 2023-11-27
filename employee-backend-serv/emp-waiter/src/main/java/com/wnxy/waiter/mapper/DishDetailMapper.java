package com.wnxy.waiter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wnxy.waiter.model.dto.DishDto;
import com.wnxy.waiter.model.entity.DishDetail;
import com.wnxy.waiter.model.entity.DishInventoryDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
public interface DishDetailMapper extends BaseMapper<DishDetail> {

    /**
     * 查询菜品DTO
     */
    List<DishDto> queryDishByTypeId(@Param("id") Integer typeId);

    List<DishInventoryDto> queryAllSalesAndInventory();

}
