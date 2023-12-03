package org.example.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fair_noFair_test {
    static class MyService{
        public Lock lock;

        public MyService(boolean fair) {
            this.lock = new ReentrantLock(fair);
        }
        public void testMethod(){
            try{
                lock.lock();
                System.out.println("testMethod "+Thread.currentThread().getName());
                //此处的 500 毫秒 用于配合 main 方法的 500 毫秒
                //使 ”array2---“线程有机会在非公平的情况下抢到锁
                Thread.sleep(500);
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class MyThread extends Thread{
        private MyService service;

        public MyThread(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService(false);
        MyThread[] array1 = new MyThread[10];
        MyThread[] array2 = new MyThread[10];
        for (int i = 0; i < array1.length; i++) {
            array1[i] = new MyThread(service);
            array1[i].setName("array1+++"+(i+1));
        }
        for (int i = 0; i < array1.length; i++) {
            array1[i].start();
        }
        for (int i = 0; i < array2.length; i++) {
            array2[i] = new MyThread(service);
            array2[i].setName("array2---"+(i+1));
        }
        Thread.sleep(500);
        for (int i = 0; i < array2.length; i++) {
            array2[i].start();
        }
    }
}
