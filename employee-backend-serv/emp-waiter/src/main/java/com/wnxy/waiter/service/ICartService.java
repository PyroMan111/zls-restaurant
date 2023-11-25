package com.wnxy.waiter.service;


public interface ICartService {
    /**
     * 添加至购物车
     */
    void addCart(Integer ordererId, Integer dishId);


}
