package com.wnxy.kitchen.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "DishWaitCookDto待做菜品查询对象", description = "")
public class DishWaitCookDto {

    private Long dishItemId;

    private Long tableId;

    private String tableNum;

    private String dishName;

    private Long count;

}
