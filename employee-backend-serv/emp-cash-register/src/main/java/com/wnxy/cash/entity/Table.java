package com.wnxy.cash.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
  @TableName("t_table")
@ApiModel(value = "Table对象", description = "")
public class Table implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Long id;

      @ApiModelProperty("桌位号")
      private String tableNumber;

      @ApiModelProperty("桌位类型外键		")
      private Integer tableTypeId;

      @ApiModelProperty("(0 空闲  1 用餐中 2 待点菜 3 预结账)")
      private Integer status;

      @ApiModelProperty("所属门店id")
      private Long restaurantId;

      @ApiModelProperty("启用状态(0 未启用 ; 1  启用 )")
      private Integer invokeStatus;

      @ApiModelProperty("楼层id(外键)")
      private Integer floorId;


}
