package com.wnxy.vip.service;

import com.wnxy.vip.entity.Vip;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-11-30
 */
public interface IVipService extends IService<Vip> {

    List queryAllVip();

    List queryTradeByVipId(Long id);
}
