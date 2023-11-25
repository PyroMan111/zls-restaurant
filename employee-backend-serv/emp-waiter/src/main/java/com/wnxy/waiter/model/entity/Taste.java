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
  @TableName("taste")
@ApiModel(value = "Taste对象", description = "")
public class Taste implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("口味名称")
      private String name;

      @ApiModelProperty("备注")
      private String remarks;

      @ApiModelProperty("状态(0 正常 1 异常)")
      private Integer status;


}
