package com.wnxy.waiter.config.anon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

// 指定注解可用范围：METHOD表示注解作用于方法上
@Target(ElementType.METHOD)
// 注解有效返回：在运行时有效
@Retention(RetentionPolicy.RUNTIME)
public @interface Idempotent {
    /**
     * 幂等校验的有效时间，在此时间范围内需要进行幂等校验
     */
    long expireTime();

    /**
     * 时间单位
     *
     * @return
     */
    TimeUnit timeunit();
}