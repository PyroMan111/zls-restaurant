package com.wnxy.waiter.model.entity;

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
 * @since 2023-11-27
 */
@Getter
@Setter
  @TableName("employee")
@ApiModel(value = "Employee对象", description = "")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("员工姓名")
      private String name;

      @ApiModelProperty("登录名")
      private String username;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("手机号")
      private String phone;

      @ApiModelProperty("性别（0 女  1 男）")
      private String sex;

      @ApiModelProperty("身份证号")
      private String idNumber;

      @ApiModelProperty("状态(0正常  1异常)")
      private Integer status;

      @ApiModelProperty("入职时间")
      private Date createTime;

      @ApiModelProperty("修改时间")
      private Date updateTime;

      @ApiModelProperty("所属门店id(外键)")
      private Integer restaurantId;

      @ApiModelProperty("角色id(0 总店长  1店长  2后厨   3服务  4收银)")
      private Integer roleId;

      @ApiModelProperty("住址")
      private String address;


}
