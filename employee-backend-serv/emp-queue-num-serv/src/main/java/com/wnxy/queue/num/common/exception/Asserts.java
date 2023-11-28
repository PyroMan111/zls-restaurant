package com.wnxy.queue.num.common.exception;


import com.wnxy.queue.num.common.enums.IResultCode;

/**
 * 自定义的断言类
 */
public abstract class Asserts {
    public static void error(boolean flag, IResultCode resultCode) {
        if (flag) {
            throw new BusinessException(resultCode);
        }
    }
}