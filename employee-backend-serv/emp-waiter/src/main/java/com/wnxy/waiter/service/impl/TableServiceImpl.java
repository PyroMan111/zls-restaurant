package com.wnxy.waiter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wnxy.waiter.model.dto.TableDto;
import com.wnxy.waiter.model.dto.TablePriceDto;
import com.wnxy.waiter.model.entity.Table;
import com.wnxy.waiter.mapper.OrderMapper;
import com.wnxy.waiter.mapper.TableMapper;
import com.wnxy.waiter.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-11-24
 */
@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<TableDto> queryAllTableDto() {
        List<TablePriceDto> tablePriceList = orderMapper.queryTablePriceList();
        List<TableDto> tableDtoList = getBaseMapper().queryAllTable();

        Map<Integer, BigDecimal> priceMap = tablePriceList.stream()
                .collect(Collectors.toMap(TablePriceDto::getTableId, TablePriceDto::getTotalPrice));

        tableDtoList.forEach(tableDto -> tableDto.setTotalPrice(priceMap.get(tableDto.getId())));

        return tableDtoList;
    }

//    public


}





/**
 * List<TablePriceDto> tablePriceList = orderMapper.queryTablePriceList();
 *
 * List<TableDto> tableDtoList = getBaseMapper().queryAllTable();
 *
 * for (TableDto tableDto : tableDtoList) {
 * for (TablePriceDto priceDto : tablePriceList) {
 * boolean equals = tableDto.getId().equals(priceDto.getTableId());
 * if (equals) {
 * tableDto.setTotalPrice(priceDto.getTotalPrice());
 * }
 * }
 * }
 *
 *
 *
 * List<TablePriceDto> tablePriceList = orderMapper.queryTablePriceList();
 * List<TableDto> tableDtoList = getBaseMapper().queryAllTable();
 *
 * Map<Integer, BigDecimal> priceMap = tablePriceList.stream()
 * .collect(Collectors.toMap(TablePriceDto::getTableId, TablePriceDto::getTotalPrice));
 *
 * tableDtoList.forEach(tableDto -> tableDto.setTotalPrice(priceMap.get(tableDto.getId())));
 *
 * return tableDtoList;
 */
