package com.wnxy.waiter.entity;

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
  @TableName("table_type")
@ApiModel(value = "TableType对象", description = "")
public class TableType implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("桌位类型")
      private String typeName;

      @ApiModelProperty("最多人数")
      private Integer maximum;

      @ApiModelProperty("最少人数")
      private Integer minmum;


}
