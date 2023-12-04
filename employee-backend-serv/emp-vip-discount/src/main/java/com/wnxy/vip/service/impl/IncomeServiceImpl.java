package com.wnxy.vip.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wnxy.vip.dto.CustomerSpentDto;
import com.wnxy.vip.dto.DiscountedAmountDto;
import com.wnxy.vip.dto.VipSpentDto;
import com.wnxy.vip.mapper.IncomeMapper;
import com.wnxy.vip.service.IncomeService;
import com.wnxy.vip.vo.IncomeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 今日收入
 */
@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeMapper incomeMapper;

    @Override
    public IncomeVo queryTodayIncome() {

        List<CustomerSpentDto> customerSpentDtos = incomeMapper.queryCustomerConsum();
        double customerSpent = customerSpentDtos.stream().mapToDouble(c->c.getCustomerSpent().doubleValue()).sum();

        List<VipSpentDto> vipSpentDtos = incomeMapper.queryVipConsum();
        double vipSpentSum = vipSpentDtos.stream().mapToDouble(c -> c.getVipSpent().doubleValue()).sum();
        double abs = Math.abs(vipSpentSum);

        List<DiscountedAmountDto> discountedAmountDtos = incomeMapper.queryDiscountedAmount();
        double discoutedSum = discountedAmountDtos.stream().mapToDouble(c -> c.getDiscountedAmount().doubleValue()).sum();

        return new IncomeVo(customerSpent,abs,discoutedSum);
    }

}
