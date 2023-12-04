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
  @TableName("vip_trade")
@ApiModel(value = "VipTrade对象", description = "")
public class VipTrade implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("会员交易流水号")
      private String tradeNumber;

      @ApiModelProperty("交易金额（消费为-   充值为+）")
      private BigDecimal money;

      @ApiModelProperty("交易类型（0充值    1消费）")
      private Integer tradeType;

      @ApiModelProperty("交易时间")
      private Date createTime;

      @ApiModelProperty("支付方式")
      private String payType;

      @ApiModelProperty("会员id(外键)")
      private Integer vipId;


}
