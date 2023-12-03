package org.example.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class awaitNanos {
        public static class MyService{
        protected Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        public void testMethod(){
            try{
                lock.lock();
                System.out.println("await begin "+System.currentTimeMillis());
                condition.awaitNanos(5_000_000_000L);
                System.out.println("await   end "+System.currentTimeMillis());
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
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
            service.testMethod();
        }
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
    }
}
