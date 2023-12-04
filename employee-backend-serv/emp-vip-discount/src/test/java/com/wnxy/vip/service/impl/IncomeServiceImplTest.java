package com.wnxy.vip.service.impl;
import com.wnxy.vip.vo.IncomeVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class IncomeServiceImplTest {
    @Autowired
    IncomeServiceImpl incomeService;



    @Test
    void getTodayIncometest(){
        IncomeVo incomeVo = incomeService.queryTodayIncome();
        System.out.println("incomeVo = " + incomeVo);
    }

}