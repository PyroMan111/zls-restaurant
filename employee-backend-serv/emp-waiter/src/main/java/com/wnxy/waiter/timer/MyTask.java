package com.wnxy.waiter.timer;

import com.wnxy.waiter.service.impl.TableServiceImpl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class MyTask extends TimerTask {



    private ConcurrentHashMap<Integer, Integer> seatStatuses; // 用餐进度存储在Map中，键为台位号，值为状态




    public void run() {
        TableServiceImpl tableService = new TableServiceImpl();

        seatStatuses = tableService.queryTableDiningStatus();
        // 查询数据库并更新用餐进度
//        String status = DBUtil.queryStatus(); // 调用DBUtil类查询数据库状态
//        if (status != null) {
//            seatStatuses.put(status); // 将查询结果存储在Map中
//        }
        // 打印用餐进度
        System.out.println("定时任务执行了" + LocalDateTime.now());
        System.out.println("台位用餐进度：" + seatStatuses);

        // 查询并打印table表的状态
        ConcurrentHashMap<Integer, Integer> map = seatStatuses;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
    public static void main(String[] args) {

        Timer timer = new Timer();
        // 在延迟0毫秒后执行任务，然后每隔1000毫秒执行一次
        timer.schedule(new MyTask(), 0, 3000);
    }
}