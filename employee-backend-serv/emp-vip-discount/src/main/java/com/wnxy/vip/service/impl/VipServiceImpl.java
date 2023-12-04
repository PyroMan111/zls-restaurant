package com.wnxy.vip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wnxy.vip.entity.Vip;
import com.wnxy.vip.mapper.VipMapper;
import com.wnxy.vip.mapper.VipTradeMapper;
import com.wnxy.vip.service.IVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author 作者
 * @since 2023-11-30
 */
@Primary
@Service
public class VipServiceImpl extends ServiceImpl<VipMapper, Vip> implements IVipService {

    @Autowired
   private VipTradeMapper vipTradeMapper;

    @Override
    public List queryAllVip() {

        return getBaseMapper().queryAllVip();
    }

    @Override
    public List queryTradeByVipId(Long id) {
        return vipTradeMapper.queryTradeByVipId(id);
    }

}
