package org.example.Mutilethread.test;


public class MyThread extends Thread {
    private int count; // 线程局部计数器

    public MyThread(String name) {
        super(name);
        this.count = 0; // 初始化计数器为0
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            count++; // 增加局部计数器的值
            System.out.println(this.getName() + ": Count = " + count);
        }
    }

    public static void main(String[] args) {
        MyThread run = new MyThread("Thread 1");
        //Signal_test thread2 = new Signal_test("Thread 2");
        Thread  thread1 = new Thread(run);
        Thread thread2 = new Thread(run);
        thread1.start();
        thread2.start();
    }
}
