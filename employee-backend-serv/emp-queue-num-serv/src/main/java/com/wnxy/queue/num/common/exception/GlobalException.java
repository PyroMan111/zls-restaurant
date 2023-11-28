package com.wnxy.queue.num.common.exception;

import com.wnxy.queue.num.common.Result;
import com.wnxy.queue.num.common.enums.IResultCode;
import com.wnxy.queue.num.common.enums.impl.ResultCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    private ResultCode resultCode;


    /**
     * 指定要处理的异常类型
     * */
    @ExceptionHandler(BusinessException.class)
    public Result handlerBusinessException(BusinessException e){
        e.printStackTrace();
        IResultCode resultCode = e.getResultCode();
        return Result.fail(resultCode);
    }


    /**
     * 如果没有精确匹配的异常，则走最大的异常
     * */
    @ExceptionHandler(Exception.class)

    public Result handlerException(Exception e){
        e.printStackTrace();
        return Result.fail(ResultCode.FAIL);
    }

}
