package com.wnxy.waiter.controller;


import com.wnxy.waiter.service.IDishDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 查看菜品详情
 *
 * @author 作者
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/dish-detail")
public class DishDetailController {
    @Autowired
    private IDishDetailService dishDetailService;


    /**
     * 展示所有菜品，库存不为0的菜品
     * 这里可以选择根据
     * */
    @GetMapping
    public List getAllDishByTypeId(@RequestParam(required = false)
                                   Integer typeId) {
        return dishDetailService.queryDishDtoByTypeId(typeId);
    }


}

