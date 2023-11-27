package com.wnxy.waiter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wnxy.waiter.mapper.DishDetailMapper;
import com.wnxy.waiter.model.dto.DishDto;
import com.wnxy.waiter.model.entity.DishDetail;
import com.wnxy.waiter.model.entity.DishInventoryDto;
import com.wnxy.waiter.service.IDishDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
@Service
public class DishDetailServiceImpl extends ServiceImpl<DishDetailMapper, DishDetail> implements IDishDetailService {

    @Autowired
    private DishDetailMapper dishDetailMapper;

    @Override
    public List<DishDto> queryDishDtoByTypeId(Integer typeId) {
        return dishDetailMapper.queryDishByTypeId(typeId);
    }

    @Override
    public List<DishInventoryDto> queryAllSalesAndInventory(){
        return dishDetailMapper.queryAllSalesAndInventory();
    }



}
