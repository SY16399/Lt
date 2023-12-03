package org.example.WUYONG;

public class VisibilityExample {
    private static int sharedValue = 0;

    public static void main(String[] args) {
        // 线程1：修改 sharedValue
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000); // 等待一段时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sharedValue = 1;
            System.out.println("Thread 1: Changed sharedValue to 1");
        });

        // 线程2：读取 sharedValue
        Thread thread2 = new Thread(() -> {
            while (sharedValue == 0) {
                // 空循环等待 sharedValue 变为非零
            }
            System.out.println("Thread 2: Read sharedValue as " + sharedValue);
        });

        thread1.start();
        thread2.start();
    }
}
