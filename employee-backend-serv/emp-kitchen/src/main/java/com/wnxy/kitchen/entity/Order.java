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
  @TableName("t_order")
@ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty("订单号")
      private String orderNumber;

      @ApiModelProperty("总价")
      private BigDecimal totalPrice;

      @ApiModelProperty("手机号")
      private String phone;

      @ApiModelProperty("桌号外键")
      private Integer tableId;

      @ApiModelProperty("创建时间")
      private Date createTime;

      @ApiModelProperty("(0 未支付 1已支付   2已取消)")
      private Integer payStatus;

      @ApiModelProperty("支付时间")
      private Date payTime;

      @ApiModelProperty("门店id  外键")
      private Integer restaurantId;

      @ApiModelProperty("收银员id(外键)")
      private Integer employeeId;

      @ApiModelProperty("订单类型(0  堂食订单   1  预约订单)")
      private Integer type;

      @ApiModelProperty("是否使用优惠券(0未使用  1已使用)")
      private Integer discountedStatus;

      @ApiModelProperty("优惠金额")
      private Integer discountedPrice;


}
