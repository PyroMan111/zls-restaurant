package com.wnxy.kitchen.entity;

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
 * @since 2023-11-28
 */
@Getter
@Setter
  @ApiModel(value = "Category对象", description = "")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("菜系名")
      private String name;

      @ApiModelProperty("创建时间")
      private Date createTime;

      @ApiModelProperty("修改时间")
      private Date updateTime;


}
