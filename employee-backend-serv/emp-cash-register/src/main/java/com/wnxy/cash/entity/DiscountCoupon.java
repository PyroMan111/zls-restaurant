package com.wnxy.cash.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * @since 2023-11-29
 */
@Getter
@Setter
  @TableName("discount_coupon")
@ApiModel(value = "DiscountCoupon对象", description = "")
public class DiscountCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("优惠券名称")
      private String name;

      @ApiModelProperty("优惠金额")
      private Integer discountPrice;

      @ApiModelProperty("满足金额")
      private Integer conditionPrice;

      @ApiModelProperty("有效期(结束)")
      private Date validityEnd;

      @ApiModelProperty("有效期(开始)")
      private Date validityStart;

      @ApiModelProperty("所属门店")
      private Integer restaurantId;

      @ApiModelProperty("发行量")
      private Integer circulation;

      @ApiModelProperty("已领取数量")
      private Integer alreadyReceived;

      @ApiModelProperty("未领取")
      private Integer unclaimed;


}
