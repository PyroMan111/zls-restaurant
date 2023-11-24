package com.wnxy.waiter.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TablePriceDto {

//    @ApiModelProperty("总价")
    private BigDecimal totalPrice;

//    @ApiModelProperty("桌号外键")
    private Integer tableId;
}
