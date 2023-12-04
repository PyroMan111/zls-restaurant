package com.wnxy.kitchen.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wnxy.kitchen.dto.DishWaitCookDto;
import com.wnxy.kitchen.service.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后厨查询菜品
 * @author 作者
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private IDishService dishService;

    @GetMapping(value = "/table/")
    public ResponseEntity queryDishByTableId(Long tableId){
        List<DishWaitCookDto> dishWaitCookDtos = dishService.queryDishByTableId(tableId);
        return ResponseEntity.ok(dishWaitCookDtos);
    }


    @GetMapping(value = "/category/")
    public ResponseEntity queryDishByCategoryId(@RequestParam Long categoryId){
        List<DishWaitCookDto> dishWaitCookDtos = dishService.queryDishByCategoryId(categoryId);
        return ResponseEntity.ok(dishWaitCookDtos);
    }


}

