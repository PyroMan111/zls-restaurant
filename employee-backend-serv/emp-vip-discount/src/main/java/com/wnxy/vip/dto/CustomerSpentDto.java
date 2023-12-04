package com.wnxy.vip.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerSpentDto {
    Long id;
    BigDecimal customerSpent;
}
