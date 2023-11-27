package com.wnxy.waiter.common.exception;

import com.wnxy.waiter.common.enums.IResultCode;

public class BusinessException extends RuntimeException {

    private IResultCode resultCode;

    public IResultCode getResultCode() {
        return resultCode;
    }


    public BusinessException() {
    }

    public BusinessException(IResultCode resultCode) {
        this.resultCode = resultCode;
    }
}
