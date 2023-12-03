package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseCondition {
    static class MyService{
        private Lock lock = new ReentrantLock();
        public Condition condition = lock.newCondition();
        public void await(){
            try{
                lock.lock();
                System.out.println(" await时间为 "+System.currentTimeMillis());
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void signal(){
            try {
                lock.lock();
                System.out.println(" signal 时间为"+System.currentTimeMillis());
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
    static class ThreadA extends Thread{
        private MyService service;

        public ThreadA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.await();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.start();
        Thread.sleep(3000);
        service.signal();
    }
}
