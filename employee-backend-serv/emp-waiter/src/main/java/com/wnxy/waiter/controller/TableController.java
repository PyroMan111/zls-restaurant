package com.wnxy.waiter.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wnxy.waiter.model.entity.Table;
import com.wnxy.waiter.model.enums.TableStatus;
import com.wnxy.waiter.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
@RestController
@RequestMapping("/table")
public class TableController {
    @Autowired
    ITableService tableService;


    /**
     * 查询所有台位接口
     */
    @GetMapping("/getAllTable")
    public List getAllTable() {
        return tableService.queryAllTableDto();
    }

        /**开台: 变更桌位状态为：status=2 待点菜*/
        @PutMapping
//        @Transactional
        public ResponseEntity confirmASeat(@RequestParam Integer tableId){
            boolean res = tableService.
                    update(Wrappers.lambdaUpdate(Table.class).
                            set(Table::getStatus, TableStatus.WAIT_TO_ORDER.getCode())
                            .eq(Table::getId, tableId));
            return ResponseEntity.ok(res);
        }

        /**
         * 展示所有菜品，库存不为0的菜品
         * */
//        @GetMapping("")
//        public Object getPage(Page page){
//
//        }



    /**
     * 选择菜品加入购物车
     * 点餐
     */
//    @PostMapping
//    public





}

