//package com.wnxy.common.util;
//
//import com.woniuxy.sddfp.common.constant.UserConstant;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * 获取token中信息的工具类
// */
//public class TokenUtil {
//    public static String getLoginUserId() {
//        // 获取request中请求头
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//        String userId = request.getHeader(UserConstant.TOKEN_USERID);
////        String token = request.getHeader("Authorization");
////        // 解析请求头
////        JwtTemplate jwtTemplate = new JwtTemplate();
////        String userId = jwtTemplate.parseToken(token, "userId").toString();
//        return userId;
//    }
//
////    public static TokenVo getTokenVo() {
////        String userId = getLoginUserId();
////
////        return null;
////    }
//}