package org.example.ReentrantLock.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockBegin2 {

    static class MyService{
        private ReadWriteLock lock = new ReentrantReadWriteLock();
        private String username = "abc";
        public void write(){
            try{
                lock.writeLock().lock();
                System.out.println("获得写锁 "+Thread.currentThread().getName()+" "+System.currentTimeMillis());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.writeLock().unlock();
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
            service.write();
        }
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadA b = new ThreadA(service);
        b.setName("B");
        b.start();
    }
}
