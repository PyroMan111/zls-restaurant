package com.wnxy.queue.num.common.constant;

import lombok.Getter;

@Getter
public enum QueueNumStatusEnum {
    CREATED_WAITING(0, "新建等待"),
    EXPIRED(1, "(超时失效)过号"),
    IN_POSITION(2, "就位");
    private Integer code;
    private String describe;

    QueueNumStatusEnum(Integer code, String describe) {
        this.code = code;
        this.describe = describe;
    }
}