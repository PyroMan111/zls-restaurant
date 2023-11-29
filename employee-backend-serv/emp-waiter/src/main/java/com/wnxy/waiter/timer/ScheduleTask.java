package com.wnxy.waiter.timer;

import com.wnxy.waiter.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ScheduleTask {

    @Autowired
    private ITableService tableService;

    //cron 表达式构成：秒 分 时 日 月 周
    //如果使用Quartz任务调度框架，有7个元素：秒 分 时 日 月 周 年（多了年）
    // cron = "0/5 * * * * *"  每5秒执行一次
    // cron = "0-5 * * * * *"  每分钟的前5秒执行
    // cron = "0,1 * * * * *"  每分钟的第1秒、第2秒执行
    // cron = "0 * * * * *"  每分钟的第1秒、第2秒执行
    @Scheduled(cron = "0/5 * * * * *",fixedDelay = 1L,fixedRate = 1L)
    public void execute() {
        System.out.println("定时执行任务：" + LocalDateTime.now());
//         查询并打印table表的状态
        ConcurrentHashMap<Integer, Integer> map = tableService.queryTableDiningStatus();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
