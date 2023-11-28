package com.wnxy.waiter.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wnxy.waiter.common.Result;
import com.wnxy.waiter.model.entity.Table;
import com.wnxy.waiter.model.enums.TableStatus;
import com.wnxy.waiter.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

    /**
     * 开台: 变更桌位状态为：status=2 待点菜
     */
    @PutMapping
    public synchronized Result confirmASeat(@RequestParam Integer tableId) {
        boolean res = tableService.
                update(Wrappers.lambdaUpdate(Table.class).
                        set(Table::getStatus, TableStatus.WAIT_TO_ORDER.getCode())
                        .eq(Table::getId, tableId));
        return Result.ok(res);
    }

    /**根据tableId查出在哪个餐厅*/

    /**定时任务，每五秒钟查询一次台位状态，(0 空闲  1 用餐中 2 待点菜 3 预结账)*/
    @GetMapping("/getTableStatus")
    public Result getTableStatus(){
        ConcurrentHashMap<Integer, Integer> map = tableService.queryTableDiningStatus();


        return Result.ok(map);
    }




    /**
     * 选择菜品加入购物车
     * 点餐
     */
//    @PostMapping
//    public


}

