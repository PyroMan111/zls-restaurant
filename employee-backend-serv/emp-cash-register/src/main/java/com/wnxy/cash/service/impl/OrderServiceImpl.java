package com.wnxy.cash.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wnxy.cash.dto.DiscountVo;
import com.wnxy.cash.dto.PrePayOrder;
import com.wnxy.cash.entity.Order;
import com.wnxy.cash.mapper.DiscountCouponMapper;
import com.wnxy.cash.mapper.OrderMapper;
import com.wnxy.cash.service.IOrderService;
import com.wnxy.cash.vo.RMBUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 123
 * @since 2023-11-29
 */
@Primary
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Autowired
    private DiscountCouponMapper couponMapper;

    /**
     * 查询所有预结账的订单
     */
    @Override
    public List<PrePayOrder> queryPrePayOrder() {
        return getBaseMapper().queryPrePayOrder();
    }

    /**
     * 查询某一笔订单详情
     */
    @Override
    public PrePayOrder getOnePreOrder(Long orderId) {
        return getBaseMapper().getOnePreOrder(orderId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean giveDiscountToOrder(DiscountVo discountVo, Long orderId) {

        /**先找出这个订单，作为本方法的全局参数副本*/
        PrePayOrder preOrder = getOnePreOrder(orderId);

        final double[] totalDiscount = {0};


        Optional.ofNullable(discountVo.getDiscountValue()).ifPresent(discount -> {
//            折扣值是一个整数，% 67就是六点七折，也就是 × 0.67
            Double discountBase = discount * 0.01;

//            确认是有优惠的订单
            preOrder.setDiscountedStatus(1);

            BigDecimal discountedPrice = preOrder.getTotalPrice().multiply(BigDecimal.valueOf(discountBase));

            BigDecimal subtract = preOrder.getTotalPrice().subtract(discountedPrice);

            totalDiscount[0] += subtract.doubleValue();

//订单总价格更新
            this.update(Wrappers.lambdaUpdate(Order.class).set(Order::getTotalPrice, discountedPrice)
                    .eq(Order::getId, orderId));

//            副本更新
            preOrder.setTotalPrice(discountedPrice);
            preOrder.setDiscountedPrice(subtract.intValue());

        });


        Optional.ofNullable(discountVo.getCouponId()).ifPresent(couponId -> {
//            找出这个券的优惠金额

//            假设这个券优惠金额是20元，
            Integer discountPrice = couponMapper.selectById(couponId).getDiscountPrice(); //这里是20

            BigDecimal subtract = preOrder.getTotalPrice().subtract(BigDecimal.valueOf(discountPrice));

//            更新扣减后的总金额
            this.update(Wrappers.lambdaUpdate(Order.class).set(Order::getTotalPrice, subtract)
                    .eq(Order::getId, orderId));

            preOrder.setTotalPrice(subtract);

            totalDiscount[0] += discountPrice;

        });

//        逐一判断每个属性是否为空
        Optional.ofNullable(discountVo.getWipeOutUnit()).ifPresent(wipeOutUnit -> {
            BigDecimal totalPrice = preOrder.getTotalPrice();

            if (RMBUnit.YUAN.equals(wipeOutUnit)) {
                // 抹去到元的操作
                totalPrice = totalPrice.setScale(0, RoundingMode.DOWN);
            } else if (RMBUnit.JIAO.equals(wipeOutUnit)) {
                // 抹去到角的操作
                totalPrice = totalPrice.setScale(1, RoundingMode.DOWN);
            } else if (RMBUnit.FEN.equals(wipeOutUnit)) {
                // 抹去到分的操作
                totalPrice = totalPrice.setScale(2, RoundingMode.DOWN);
            }

            //            更新扣减后的总金额
            this.update(Wrappers.lambdaUpdate(Order.class).set(Order::getTotalPrice, totalPrice)
                    .set(Order::getDiscountedPrice, totalDiscount[0])
                    .eq(Order::getId, orderId));

//            订单不可以优惠成负数
            if (totalPrice.doubleValue() <= 0) {
                    throw new IllegalArgumentException();
            }
        });

        return true;
    }


    /**给某笔账单搞优惠的接口：根据折扣信息（比如打八折就是80%），优惠券识别码，元或角或分三者之一抹零的选项及抹到多少*/

    /**模拟用户支付，set pay_status = 1 ,还有set pay_time*/


}
