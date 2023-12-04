package com.wnxy.vip.entity;

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
 * @since 2023-11-30
 */
@Getter
@Setter
  @TableName("vip")
@ApiModel(value = "Vip对象", description = "")
public class Vip implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("会员号")
      private String vipCode;

      @ApiModelProperty("生日")
      private Date birthday;

      @ApiModelProperty("姓名")
      private String name;

      @ApiModelProperty("会员等级（外键）")
      private Integer vipTypeId;

      @ApiModelProperty("用户id（外键）")
      private Integer userId;

      @ApiModelProperty("手机号（冗余字段）")
      private String phone;

      @ApiModelProperty("性别（冗余字段）")
      private String sex;

      @ApiModelProperty("总消费金额")
      private BigDecimal totalExpense;

      @ApiModelProperty("账户余额")
      private BigDecimal balance;

      @ApiModelProperty("餐厅id（外键）")
      private Integer restaurantId;

      @ApiModelProperty("创建日期")
      private Date createTime;

      @ApiModelProperty("消费次数")
      private Integer consumeCount;

      @ApiModelProperty("状态（0 正常  1 异常）")
      private Integer status;


}
