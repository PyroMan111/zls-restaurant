package com.wnxy.cash.dto;


import com.wnxy.cash.vo.RMBUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountVo {

    /**
     * 举例，打九折、扣减20元的券、打折用券后不论多少钱，假设是80.9元，给角抹零，就是直接扔掉小数点后面的数字，80元
     */

    private Integer discountValue;

    private String couponId;

    /**
     * 元抹零：112元-》110
     * 角抹零：86.9-》86.0
     * 分抹零：123.45-》123.4元
     */
    private RMBUnit wipeOutUnit;


}
