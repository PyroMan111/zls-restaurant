package com.wnxy.waiter.model.enums;

import lombok.Getter;

@Getter
public enum TableStatus {
    IDLE(0, "空闲"),
    DINING(1, "用餐中"),
    WAIT_TO_ORDER(2, "待点菜"),
    PRE_PAY(3, "预结账");

    private int code;
    private String desc;

    TableStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}