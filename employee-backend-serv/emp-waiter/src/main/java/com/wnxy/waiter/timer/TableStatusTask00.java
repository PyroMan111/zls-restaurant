package com.wnxy.waiter.timer;

import com.wnxy.waiter.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
@Component
public class TableStatusTask00 implements Runnable {
    @Autowired
    private ITableService tableService;



    @Override
    public void run() {

        // 查询并打印table表的状态
        ConcurrentHashMap<Integer, Integer> map = queryTableStatus();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

//        System.out.println("Table status: " + status);
//        queryTableStatus().get()

    }

    private ConcurrentHashMap<Integer, Integer> queryTableStatus() {
        // 这里替换为你的查询table状态的代码
        return tableService.queryTableDiningStatus();
    }


    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // 在延迟0秒后开始执行任务，然后每隔5秒执行一次
        executor.scheduleAtFixedRate(new TableStatusTask00(), 0, 5, TimeUnit.SECONDS);

    }
}