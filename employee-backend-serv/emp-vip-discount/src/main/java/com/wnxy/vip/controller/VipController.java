package com.wnxy.vip.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wnxy.vip.entity.Vip;
import com.wnxy.vip.service.IVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 会员服务
 *
 * @author 作者
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/vip")
public class VipController {

    @Autowired
    private IVipService vipService;

    @GetMapping
    public ResponseEntity queryAllVip() {
        return ResponseEntity.ok(vipService.queryAllVip());
    }

    @GetMapping( "/getTrade/{id}")
    public ResponseEntity queryTradeByVipId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(vipService.queryTradeByVipId(id));
    }
//    
    @PutMapping(value = "/recharge/")
    public ResponseEntity rechargeVipById(@RequestParam Long id,@RequestParam BigDecimal rechargeAmount){
        BigDecimal currentBalance = vipService.getBaseMapper().selectById(id).getBalance();
        boolean res = vipService.
                update(Wrappers.lambdaUpdate(Vip.class).
                        set(Vip::getBalance,currentBalance.add(rechargeAmount)).
                        eq(Vip::getId, id));


        return ResponseEntity.ok(res);
    }
    @PutMapping
    public ResponseEntity updateVip(@RequestBody Vip vip){
        return ResponseEntity.ok(vipService.updateById(vip));
    }

}

