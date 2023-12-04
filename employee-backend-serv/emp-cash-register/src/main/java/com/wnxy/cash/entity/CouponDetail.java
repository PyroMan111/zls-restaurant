package com.wnxy.cash.entity;

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
 * 优惠券详情表
 * </p>
 *
 * @author 作者
 * @since 2023-11-29
 */
@Getter
@Setter
  @TableName("coupon_detail")
@ApiModel(value = "CouponDetail对象", description = "优惠券详情表")
public class CouponDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("优惠券详情ID  ")
        private Integer id;

      @ApiModelProperty("优惠券名称")
      private String couponName;

      @ApiModelProperty("优惠券面值")
      private BigDecimal couponValue;

      @ApiModelProperty("优惠券类型")
      private String couponType;

      @ApiModelProperty("优惠券状态")
      private Integer status;

      @ApiModelProperty("发放量")
      private Integer issuedCount;

      @ApiModelProperty("使用量")
      private Integer usedCount;

      @ApiModelProperty("创建时间 ")
      private Date createdDatetime;

      @ApiModelProperty("失效时间")
      private Date expiredDatetime;

      @ApiModelProperty("使用门槛")
      private BigDecimal usageThreshold;

      @ApiModelProperty("每人限额券数")
      private Integer limitPerUser;

      @ApiModelProperty("所属门店ID（外键）")
      private Integer storeId;

      @ApiModelProperty("已领取数")
      private Integer collectedCount;

      @ApiModelProperty("待领取数")
      private Integer pendingCount;

      @ApiModelProperty("未使用数量")
      private Integer unusedCount;

      @ApiModelProperty("逻辑删除标志")
      private Integer isDeleted;


}
