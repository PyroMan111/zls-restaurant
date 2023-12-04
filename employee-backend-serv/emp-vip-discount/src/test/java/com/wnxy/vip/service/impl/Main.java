package com.wnxy.vip.service.impl;

import java.util.ArrayList;
import java.util.List;  
import java.util.Random;  
import java.util.concurrent.ThreadLocalRandom;  
import java.util.concurrent.TimeUnit;  
  
class VIP extends Thread {  
    private List<String> numbers;  
    private String name;  
    private int timeout;  
    private Random random;  
  
    public VIP(List<String> numbers, String name, int timeout) {  
        this.numbers = numbers;  
        this.name = name;  
        this.timeout = timeout;  
        this.random = new Random();  
    }  
  
    public void run() {  
        System.out.println(name + " starts to choose a number");
        while (!numbers.isEmpty()) {  
            int numIndex = ThreadLocalRandom.current().nextInt(numbers.size());  
            String number = numbers.get(numIndex);  
            System.out.println(name + " chooses number " + number);
            numbers.remove(numIndex);  
            try {  
                TimeUnit.SECONDS.sleep(random.nextInt(timeout)); // 随机等待一段时间，模拟选房过程  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println(name + " finishes choosing numbers");  
    }  
}  
  
public class Main {
    public static void main(String[] args) {
        // 初始化号码池，例如1-100的号码，每个号码代表一个普通号或VIP号。可以根据实际需求调整号码范围和数量。VIP号的选房时间为普通号的2倍。
        List<String> numbers = new ArrayList<>();
        // 例如：VIP号选房时间为2秒，普通号选房时间为1秒。在上面的例子中，我们使用了一个List来存储号码，可以根据实际需求选择其他数据结构。
        // 在上面的例子中，我们使用了Java的线程类Thread来创建并启动线程。每个线程代表一个选房者，会从号码池中随机选择一个号码，并等待一段时间来模拟选房过程。
        // 当号码池为空时，线程会结束执行。在这个例子中，我们使用了Java的线程类Thread来创建并启动线程。
        // 每个线程代表一个选房者，会从号码池中随机选择一个号码，并等待一段时间来模拟选房过程。当号码池为空时，线程会结束执行。
        // 当有多个线程并发执行时，会依次显示每个线程的选择结果。例如：VIP1选择了1号，VIP2选择了2号，普通1选择了3号...当所有号码都被选择后，所有线程都会结束执行。

        for (int i = 1; i <= 100; i++) {
            numbers.add(String.valueOf(i));
        }

        // 创建并启动多个VIP线程，每个线程会从号码池中随机选择一个号码，并等待一段时间来模拟选房过程
        for (int i = 1; i <= 5; i++) {
            VIP thread = new VIP(numbers, "VIP" + i, 2); // VIP号的选房时间为普通号的2倍，这里设置为2秒
            thread.start();
        }
    }

}
