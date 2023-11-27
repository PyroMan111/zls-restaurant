package com.wnxy.waiter.model.entity;

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
 * @since 2023-11-24
 */
@Getter
@Setter
  @TableName("dish_detail")
@ApiModel(value = "DishDetail对象", description = "")
public class DishDetail implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("商品名称")
      private String name;

      @ApiModelProperty("口味")
      private Integer tasteId;

      @ApiModelProperty("规格")
      private Integer sizeId;

      @ApiModelProperty("菜品介绍")
      private String introduction;

      @ApiModelProperty("库存")
      private Integer inventory;

      @ApiModelProperty("状态(0 停售 1 在售)")
      private Integer status;


}
