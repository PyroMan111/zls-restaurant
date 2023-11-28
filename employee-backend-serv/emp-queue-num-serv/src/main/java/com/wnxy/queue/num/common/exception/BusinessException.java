package com.wnxy.queue.num.common.exception;

import com.wnxy.queue.num.common.enums.IResultCode;

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
