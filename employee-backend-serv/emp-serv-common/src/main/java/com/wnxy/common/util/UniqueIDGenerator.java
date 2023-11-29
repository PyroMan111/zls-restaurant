package com.wnxy.common.util;

import java.util.UUID;

public class UniqueIDGenerator {

    /**
     * 根据业务编码，生成唯一业务编号
     */
    public static String generator(String prefixCode) {
        return prefixCode + UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

}
