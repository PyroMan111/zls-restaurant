package com.wnxy.waiter.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wnxy.waiter.mapper.OrderMapper;
import com.wnxy.waiter.mapper.TableMapper;
import com.wnxy.waiter.model.dto.TableDto;
import com.wnxy.waiter.model.dto.TablePriceDto;
import com.wnxy.waiter.model.dto.TableStatusDto;
import com.wnxy.waiter.model.entity.Table;
import com.wnxy.waiter.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
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


    @Override
    public Long queryRestaurantIdByTable(Long tableId) {
        LambdaQueryChainWrapper<Table> wrapper = this.lambdaQuery()
                .select(Table::getRestaurantId).eq(Objects.nonNull(tableId), Table::getId, tableId);

        Table table = this.getOne(wrapper);

        if (table == null) {
            throw new IllegalArgumentException("没有找到此桌位号: " + tableId);
        }

        return table.getRestaurantId();
    }

    /**(0 空闲  1 用餐中 2 待点菜 3 预结账)*/
    /**
     * 本来一个桌位是空闲，然后有两批人进入小程序查看，此时一个桌位有人开台进入待点菜状态，
     * 然后另一个人正好也选了这个，此时都会调用confirmASeat这个接口去set status = 2会出现并发安全问题
     */
    @Override
    @Scheduled
    public ConcurrentHashMap<Integer, Integer> queryTableDiningStatus() {

        List<TableStatusDto> tableStatusList = this.getBaseMapper().queryAllTableStatus();


//查询结果是: 桌ID   用餐状态
//           id	   status
//           1	    2
//           2	    0
//           3	    1
//           4	    1
        ConcurrentHashMap<Integer, Integer> resultMap = new ConcurrentHashMap<>();
        for (TableStatusDto table : tableStatusList) {
            resultMap.put(table.getId(), table.getStatus());

        }
        return resultMap;
    }


}


/**
 * List<TablePriceDto> tablePriceList = orderMapper.queryTablePriceList();
 * <p>
 * List<TableDto> tableDtoList = getBaseMapper().queryAllTable();
 * <p>
 * for (TableDto tableDto : tableDtoList) {
 * for (TablePriceDto priceDto : tablePriceList) {
 * boolean equals = tableDto.getId().equals(priceDto.getTableId());
 * if (equals) {
 * tableDto.setTotalPrice(priceDto.getTotalPrice());
 * }
 * }
 * }
 * <p>
 * <p>
 * <p>
 * List<TablePriceDto> tablePriceList = orderMapper.queryTablePriceList();
 * List<TableDto> tableDtoList = getBaseMapper().queryAllTable();
 * <p>
 * Map<Integer, BigDecimal> priceMap = tablePriceList.stream()
 * .collect(Collectors.toMap(TablePriceDto::getTableId, TablePriceDto::getTotalPrice));
 * <p>
 * tableDtoList.forEach(tableDto -> tableDto.setTotalPrice(priceMap.get(tableDto.getId())));
 * <p>
 * return tableDtoList;
 */
