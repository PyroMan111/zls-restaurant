package com.wnxy.vip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wnxy.vip.dto.VipDto;
import com.wnxy.vip.entity.Vip;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-30
 */
public interface VipMapper extends BaseMapper<Vip> {

    List<VipDto> queryAllVip();

}
