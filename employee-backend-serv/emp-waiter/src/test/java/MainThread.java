class Runner implements Runnable {  
    private String name;  
    private int speed;  
  
    public Runner(String name, int speed) {  
        this.name = name;  
        this.speed = speed;  
    }  
  
    public void run() {  
        for (int i = 0; i < 100; i++) {  
            System.out.println(name + " has run " + i + " meters");  
            try {  
                Thread.sleep(speed);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println(name + " has finished running");  
    }  
}  
  
public class MainThread {
    public static void main(String[] args) {  
        Thread thread1 = new Thread(new Runner("张三", 1000));
        Thread thread2 = new Thread(new Runner("李四", 2000));
        thread1.start();  
        thread2.start();  
    }  
}