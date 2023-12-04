package com.wnxy.cash.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *  ps：communication链接申请无效，单干
 * @author 作者
 * @since 2023-11-29
 */
@RestController
@RequestMapping("/discount-coupon")
public class DiscountCouponController {

    /**查询所有预结账的订单*/

    /**查询某一笔订单详情*/

    /**给某笔账单搞优惠的接口：根据折扣信息（比如打八折就是80%），优惠券识别码，元或角或分三者之一抹零的选项及抹到多少*/

    /**举例，打九折、扣减20元的券、打折用券后不论多少钱，假设是80.9元，给角抹零，就是直接扔掉小数点后面的数字，81元抹掉就是*/

    /**模拟用户支付，set pay_status = 1 ,还有set pay_time*/

}

