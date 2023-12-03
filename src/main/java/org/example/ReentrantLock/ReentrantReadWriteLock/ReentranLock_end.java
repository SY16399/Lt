package org.example.ReentrantLock.ReentrantReadWriteLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranLock_end {
    static class MyService{
        private Lock lock = new ReentrantLock();
        private String username = "abc";
        Condition condition = lock.newCondition();
        public void testMethod1(){
            try{
                lock.lock();
                System.out.println("begin "+Thread.currentThread().getName()+" "+System.currentTimeMillis());
                System.out.println("print service " +username);
                Thread.sleep(4000);
                System.out.println("    end "+Thread.currentThread().getName()+" "+System.currentTimeMillis());
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
            service.testMethod1();
        }
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.start();
        ThreadA b = new ThreadA(service);
        b.start();
    }
}
