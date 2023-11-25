package com.wnxy.waiter.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@ApiModel("菜品展示对象Dto")
public class DishDto {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("菜品名称")
    private String dishName;

    @ApiModelProperty("价钱")
    private BigDecimal price;

   @ApiModelProperty("规格名")
    private String sizeName;

   @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("口味")
    private String taste;

    @ApiModelProperty("菜类别")
    private String dishType;

    @ApiModelProperty("余量")
    private Integer inventory;



}
