package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MustUseMoreCondition_Error {
    static class MyService {
        private Lock lock = new ReentrantLock();
        public Condition condition = lock.newCondition();

        public void awaitA() {
            try {
                lock.lock();
                System.out.println("begin awaitA时间为" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
                condition.await();
                System.out.println("    end awaitA时间为" + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void awaitB() {
            try {
                lock.lock();
                System.out.println("begin awaitB时间为 " + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
                condition.await();
                System.out.println("    end awaitB时间为 " + System.currentTimeMillis() + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void signalAll() {
            try {
                lock.lock();
                System.out.println("    signalAll时间为 " + System.currentTimeMillis() + " ThreadName=" + Thread.currentThread().getName());
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    static class ThreadA extends Thread {
        private MyService service;

        public ThreadA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.awaitA();
        }
    }
    static class ThreadB extends Thread{
        private MyService service;

        public ThreadB(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.awaitB();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b  = new ThreadB(service);
        b.setName("B");
        b.start();
        Thread.sleep(3000);
        service.signalAll();
    }
}
