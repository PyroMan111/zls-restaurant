package com.wnxy.vip.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeVo {
    private Double customerSpent;
    private Double vipSpent;
    private Double discountedAmount;


}
