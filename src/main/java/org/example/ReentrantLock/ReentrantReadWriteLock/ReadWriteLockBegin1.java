package org.example.ReentrantLock.ReentrantReadWriteLock;

import org.example.ReentrantLock.awaitNanos;

import java.util.concurrent.locks.*;

public class ReadWriteLockBegin1 {
    static class MyService{
        private ReadWriteLock lock = new ReentrantReadWriteLock();
        private String username = "abc";
        public void testMethod1(){
            try{
                lock.readLock().lock();
                System.out.println("begin "+Thread.currentThread().getName()+" "+System.currentTimeMillis());
                System.out.println("print service " +username);
                Thread.sleep(4000);
                System.out.println("    end "+Thread.currentThread().getName()+" "+System.currentTimeMillis());
                lock.readLock().unlock();
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
