package com.wnxy.vip.mapper;

import com.wnxy.vip.entity.VipTrade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wnxy.vip.entity.VipType;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-30
 */
public interface VipTradeMapper extends BaseMapper<VipTrade> {
    List<VipTrade> queryTradeByVipId(Long id);

}
