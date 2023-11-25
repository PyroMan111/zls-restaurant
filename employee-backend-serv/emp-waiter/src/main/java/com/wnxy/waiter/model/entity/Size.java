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
  @TableName("size")
@ApiModel(value = "Size对象", description = "")
public class Size implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("规格名称")
      private String name;

      @ApiModelProperty("备注")
      private String remark;

      @ApiModelProperty("状态")
      private Integer status;


}
