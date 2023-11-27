package com.wnxy.waiter.config.anon;

import cn.hutool.json.JSONUtil;
import com.wnxy.waiter.common.Result;
import com.wnxy.waiter.common.enums.impl.BusinessCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.http.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class IdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 在执行控制器方法之前执行
     *
     * @param request  请求对象
     * @param response 响应对象
     * @param handler  处理器（控制器对象）
     * @return 返回true表示放行； false不放行
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // handler.getClass() = class org.springframework.web.method.HandlerMethod
        System.out.println("handler.getClass() = " + handler.getClass());
        // 如果handler不是HandlerMethod类型，不是拦截控制器方法，就直接放行
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 转换为HandlerMethod类型，这样就可以使用其提供的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 判断当前执行的方法上是否有指定的注解
        boolean flag = handlerMethod.hasMethodAnnotation(Idempotent.class);
        if (!flag) {
            // 方法上没有使用幂等校验注解，返回true放行
            return true;
        }
        // 通过handlerMethod对象获取方法上指定的注解
        Idempotent idempotent = handlerMethod.getMethodAnnotation(Idempotent.class);
        // 获取请求头
        String uniquecode = request.getHeader("uniquecode");
        if (redisTemplate.hasKey(uniquecode)) {
            // 返回失败
            responseError(response);
            return false;
        } else {
            // 幂等校验标记保存到redis中
            redisTemplate.opsForValue().set(uniquecode, 1, idempotent.expireTime(),
                    idempotent.timeunit());
        }
        return true;

    }

    private static void responseError(HttpServletResponse response) throws IOException {
        Result result = Result.ok(BusinessCode.ORDER_REPEAT_SUBMIT);
        String resultJson = JSONUtil.toJsonStr(result);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(resultJson);
    }
}