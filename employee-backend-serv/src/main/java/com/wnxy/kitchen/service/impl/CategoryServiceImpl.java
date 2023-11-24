package com.wnxy.kitchen.service.impl;

import com.wnxy.common.entity.Category;
import com.wnxy.kitchen.mapper.CategoryMapper;
import com.wnxy.kitchen.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-23
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
