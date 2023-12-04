package com.wnxy.vip.mapper;

import com.wnxy.vip.dto.CustomerSpentDto;
import com.wnxy.vip.dto.DiscountedAmountDto;
import com.wnxy.vip.dto.VipSpentDto;
import com.wnxy.vip.vo.IncomeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-30
 */
@Mapper
public interface IncomeMapper  {

    List<IncomeVo> queryTodayIncome();
    List<CustomerSpentDto> queryCustomerConsum();

    List<VipSpentDto> queryVipConsum();

    List<DiscountedAmountDto> queryDiscountedAmount();


}
