import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
public class TestThread {
}

class MyThread extends Thread {
    public static void main(String[] args) {
//        MyThread t1 = new MyThread();
//        t1.start();
//        MyThread t2 = new MyThread();
//        t2.start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("i = " + i);

            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("i = " + i);
            }
        }).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class App3 {
    public static void main(String[] args) {
        //1、计算任务，实现Callable接口
        Callable<String> callable = () -> {
            int sum = 0;
            for (int i = 0; i < 20; i++) {
                sum += i;
                // 耗时操作
                Thread.sleep(100);
            }
            return "计算结果：" + sum;
        };
        //2、创建FutureTask，传入callable对象
        FutureTask<String> futureTask = new FutureTask<>(callable);
        //3、创建启动线程
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            String result = futureTask.get(1, TimeUnit.SECONDS);
            System.out.println("result = " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            // 超时中断执行
            futureTask.cancel(true);
            System.out.println("超时中断执行");
        }
    }
}
