package com.wnxy.queue.num.entity;

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
 * @since 2023-11-24
 */
@Getter
@Setter
  @TableName("queue_number")
@ApiModel(value = "QueueNumber对象", description = "")
public class QueueNumber implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("主键")
        private Integer id;

      @ApiModelProperty("楼层外键")
      private Integer floorId;

      @ApiModelProperty("用餐人数")
      private Integer population;

      @ApiModelProperty("手机号")
      private String phone;

      @ApiModelProperty("号状态(0 新建等待 1 过号 2 就位 )")
      private Integer status;

      @ApiModelProperty("创建时间")
      private Date createTime;

      @ApiModelProperty("叫号时间")
      private Date callTime;

      @ApiModelProperty("取号号码")
      private String code;


}
