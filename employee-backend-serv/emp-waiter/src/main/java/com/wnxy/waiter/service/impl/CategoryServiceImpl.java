package com.wnxy.waiter.service.impl;

import com.wnxy.waiter.model.entity.Category;
import com.wnxy.waiter.mapper.CategoryMapper;
import com.wnxy.waiter.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
