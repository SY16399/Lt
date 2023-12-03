package org.example;

public class ThreadAliveTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();

        Thread.sleep(1000); // 等待一段时间，确保线程有足够的时间执行

        System.out.println("Thread.currentThread().isAlive(): " + Thread.currentThread().isAlive());
        System.out.println("thread.isAlive(): " + thread.isAlive());
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Inside run() method: " + Thread.currentThread().isAlive());
        System.out.println("Inside run() method: " + this.isAlive());
    }
}
