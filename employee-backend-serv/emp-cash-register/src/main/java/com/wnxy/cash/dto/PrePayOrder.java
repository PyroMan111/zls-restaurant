package com.wnxy.cash.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "预支付订单对象", description = "")
public class PrePayOrder {

    @ApiModelProperty("主键")

    private Long id;

    @ApiModelProperty("订单号")
    private String orderNumber;

    @ApiModelProperty("手机号")
    private String phone;


    @ApiModelProperty("(0 未支付 1已支付   2已取消)")
    private Integer payStatus;

    @ApiModelProperty("是否使用优惠券(0未使用  1已使用)")
    private Integer discountedStatus;

    @ApiModelProperty("优惠金额")
    private Integer discountedPrice;

    @ApiModelProperty("总价")
    private BigDecimal totalPrice;

    @ApiModelProperty("支付时间")
    private Date payTime;

    @ApiModelProperty("订单类型(0  堂食订单   1  预约订单)")
    private Integer type;

    @ApiModelProperty("台位id")
    private Long tableId;

}
