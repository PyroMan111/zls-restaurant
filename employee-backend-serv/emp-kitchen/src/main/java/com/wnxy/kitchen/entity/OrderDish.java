package com.wnxy.kitchen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2023-11-28
 */
@Getter
@Setter
  @TableName("order_dish")
@ApiModel(value = "OrderDish对象", description = "")
public class OrderDish implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("订单id（外键）")
      private Long orderId;

      @ApiModelProperty("菜品id（外键）")
      private Long dishId;

      @ApiModelProperty("菜品数量")
      private Integer count;

      @ApiModelProperty("总价")
      private BigDecimal totalPrice;

      @ApiModelProperty("创建时间")
      private Date createTime;

      @ApiModelProperty("修改时间")
      private Date updatTime;


}
