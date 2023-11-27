package com.wnxy.waiter.common.constant;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {
    NO_PAY(0, "未支付"),
    ALREADY_PAID(1, "已支付"),
    ALREADY_CANCEL(2, "已取消");
    private Integer code;
    private String describe;

    OrderStatusEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
}