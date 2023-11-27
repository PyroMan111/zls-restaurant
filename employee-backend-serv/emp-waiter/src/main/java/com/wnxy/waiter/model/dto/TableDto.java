package com.wnxy.waiter.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel("桌位展示对象")
public class TableDto {


    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("桌位号")
    private String tableNumber;

    @ApiModelProperty("(0 空闲  1 用餐中 2 待点菜 3 预结账)")
    private Integer status;

    @ApiModelProperty("(0 空闲  1 用餐中 2 待点菜 3 预结账)")
    private Integer restaurantId;

    @ApiModelProperty("桌位类型")
    private String typeName;

    @ApiModelProperty("最少人数")
    private Integer minmum;

    @ApiModelProperty("总价")
    private BigDecimal totalPrice;
}
