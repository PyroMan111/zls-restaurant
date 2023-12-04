package com.wnxy.vip.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
 * @since 2023-11-30
 */
@Getter
@Setter
  @TableName("vip_type")
@ApiModel(value = "VipType对象", description = "")
public class VipType implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("等级名")
      private String typeName;

      @ApiModelProperty("折扣数")
      private BigDecimal discount;

      @ApiModelProperty("消费金额")
      private Integer consumptionMoney;

      @ApiModelProperty("门店id(外键)")
      private Integer restaurantId;


}
