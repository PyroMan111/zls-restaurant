package com.wnxy.waiter.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DishInventoryDto库存排序对象", description = "")
public class DishInventoryDto {

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("菜品名称")
    private String name;

    @ApiModelProperty("月销量")
    private Long monthlySales;

    @ApiModelProperty("菜品图")
    private String image;

    @ApiModelProperty("库存")
    private Long inventory;


}
