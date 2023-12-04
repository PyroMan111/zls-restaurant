package com.wnxy.cash.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wnxy.cash.dto.DiscountVo;
import com.wnxy.cash.dto.PrePayOrder;
import com.wnxy.cash.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收银员操作
 *
 * @author 作者
 * @since 2023-11-29
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    /**
     * 查询所有预结账的订单
     */
    @GetMapping
    public ResponseEntity queryPrePayOrder() {
        List<PrePayOrder> prePayOrders = orderService.queryPrePayOrder();
        return ResponseEntity.ok(prePayOrders);
    }

    /**查询某一笔订单详情*/
    @GetMapping("/{orderId}")
    public ResponseEntity queryOnePrePayOrder(@PathVariable Long orderId) {
        PrePayOrder order = orderService.getOnePreOrder(orderId);
        return ResponseEntity.ok(order);

    }


    /**给某笔账单搞优惠的接口：根据折扣信息（比如打八折就是80%），优惠券识别码，元或角或分三者之一抹零的选项及抹到多少*/
    @PostMapping
    public ResponseEntity giveDiscountToOrder(@RequestBody DiscountVo discountVo,@RequestParam Long orderId){
        boolean res = this.orderService.giveDiscountToOrder(discountVo,orderId);
        return ResponseEntity.ok("OK");
    }



    /**模拟用户支付，set pay_status = 1 ,还有set pay_time*/

//    /**今日收入接口*/
//
//    @GetMapping("/{getIncome}")
//    public ResponseEntity getPage(String ){
//
//        return null;
//    }



}

