package com.wnxy.waiter.model.vo;

import com.wnxy.waiter.model.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor// 购物车
public class CartVo {
    // 所有的购物项集合
    private List<CartItemDto> CartItemDto;
    // 总价
    private BigDecimal totalPrice;
    // 当前用户
    private Integer ordererId;
}