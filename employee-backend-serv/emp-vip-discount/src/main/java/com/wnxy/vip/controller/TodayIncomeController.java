package com.wnxy.vip.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wnxy.vip.entity.Vip;
import com.wnxy.vip.service.IVipService;
import com.wnxy.vip.service.IncomeService;
import com.wnxy.vip.service.impl.IncomeServiceImpl;
import com.wnxy.vip.vo.IncomeVo;
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
@RequestMapping("/income")
public class TodayIncomeController {

    @Autowired
    private IncomeServiceImpl incomeService;

    @GetMapping
    public ResponseEntity queryTodayIncome() {

        IncomeVo incomeVo = incomeService.queryTodayIncome();
//        String todayIncome = incomeVo.toString();
        System.out.println("incomeVo = " + incomeVo);

        return ResponseEntity.ok(incomeVo);
    }


}

