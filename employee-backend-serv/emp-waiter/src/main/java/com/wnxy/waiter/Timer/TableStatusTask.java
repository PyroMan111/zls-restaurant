package com.wnxy.waiter.Timer;

import com.wnxy.waiter.common.Result;
import com.wnxy.waiter.service.ITableService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TableStatusTask {

    private final ITableService tableService;

    public TableStatusTask(ITableService tableService) {
        this.tableService = tableService;
    }

    @Scheduled(fixedRate = 5000) // 每5秒钟执行一次  
    public void updateTableStatus() {
        ConcurrentHashMap<Integer, Integer> map = tableService.queryTableDiningStatus();
        // 将查询结果存储到内存中，供其他方法使用  
        // ...
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    @GetMapping("/getTableStatus")
    public Result getTableStatus() {
        ConcurrentHashMap<Integer, Integer> map = tableService.queryTableDiningStatus();
        return Result.ok(map);
    }

    public static void main(String[] args) {


    }
}