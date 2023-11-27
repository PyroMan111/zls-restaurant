package com.wnxy.waiter.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class IdGenerator {
    private int id;
    private Lock lock = new ReentrantLock();

    public IdGenerator() {
        this(1); // 初始化orderId为1
    }

    public IdGenerator(int id) {
        this.id = id;
    }

    public int getNextOrderId() {
        lock.lock(); // 加锁
        try {
            ++id;
            return id; // 返回当前的orderId，并自增1
        } finally {
            lock.unlock(); // 释放锁
        }
    }
}