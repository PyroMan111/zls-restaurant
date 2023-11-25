package com.wnxy.waiter.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
@Getter
@Setter
@TableName("dish")
@ApiModel(value = "Dish对象", description = "")
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("菜品名称")
    private String name;

    @ApiModelProperty("菜品分类id（外键）")
    private Integer categoryId;

    @ApiModelProperty("菜品价格")
    private BigDecimal price;

    @ApiModelProperty("菜品图")
    private String image;

    @ApiModelProperty("门店id（外键）")
    private Integer restaurantId;

    @ApiModelProperty("状态（0  停售  1在售）")
    private Integer status;

    @ApiModelProperty("月销量")
    private Integer monthlySales;


}
