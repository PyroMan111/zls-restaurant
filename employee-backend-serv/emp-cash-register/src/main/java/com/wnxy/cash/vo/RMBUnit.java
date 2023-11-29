package com.wnxy.cash.vo;

public enum RMBUnit {

    // 枚举对象（调用枚举构造函数）
    YUAN("YUAN", "元"),
    JIAO("JIAO", "角"),
    FEN("FEN", "分");
    private String code;
    private String desc;

    // 枚举构造函数
    RMBUnit(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
