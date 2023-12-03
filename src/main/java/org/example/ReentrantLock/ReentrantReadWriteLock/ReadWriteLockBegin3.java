package org.example.ReentrantLock.ReentrantReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockBegin3 {
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
        public void read(){
            try{
                lock.readLock().lock();
                System.out.println(" 获得读锁 "+Thread.currentThread().getName()+" "+System.currentTimeMillis());
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.readLock().unlock();
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
    static class ThreadB extends Thread{
        private MyService service;

        public ThreadB(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.read();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        Thread.sleep(500);
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
    }
}
