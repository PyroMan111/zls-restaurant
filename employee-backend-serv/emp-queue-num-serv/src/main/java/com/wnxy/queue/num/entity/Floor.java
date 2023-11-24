package com.wnxy.queue.num.entity;

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
  @TableName("floor")
@ApiModel(value = "Floor对象", description = "")
public class Floor implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("楼层")
      private String floorName;

      @ApiModelProperty("楼层状态( 0  正常 1 异常)")
      private Integer status;


}
