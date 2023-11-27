package com.wnxy.waiter.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {
    // 菜品ID
    private Long dishId;
    // 菜品名称
    private String name;
    // 菜品图片
    private String image;
    // 菜品单价
    private BigDecimal price;
    // 购买数量
    private Integer buycount;
    // 小计 = 单价 * 数量
    private BigDecimal sumPrice;

    //    购买这件菜品的下单人的Id
    private String ordererId;

}

