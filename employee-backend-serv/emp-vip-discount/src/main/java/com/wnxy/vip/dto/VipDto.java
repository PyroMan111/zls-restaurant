package com.wnxy.vip.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class VipDto {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("生日")
    private Date birthday;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号（冗余字段）")
    private String phone;

    @ApiModelProperty("性别（冗余字段）")
    private String sex;

    @ApiModelProperty("总消费金额")
    private BigDecimal totalExpense;

    @ApiModelProperty("账户余额")
    private BigDecimal balance;

    @ApiModelProperty("等级名")
    private String typeName;

    @ApiModelProperty("创建日期")
    private Date createTime;


}
