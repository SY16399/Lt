package org.example.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTestMoreMethod {

    static class MyService{
        private Lock lock= new ReentrantLock();
        public void methodA(){
            try{
                lock.lock();
                System.out.println("methodA begin ThreadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("methodA end ThreadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void methodB(){
            try{
                lock.lock();
                System.out.println("methodB begin ThreadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("methodB end ThreadName="+Thread.currentThread().getName()+" time="+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    }
    static class ThreadA extends  Thread{
        private MyService service;

        public ThreadA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.methodA();
        }
    }
    static class ThreadAA extends  Thread{
        private MyService service;

        public ThreadAA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.methodA();
        }
    }
    static class ThreadB extends Thread{
        private MyService service;

        public ThreadB(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.methodB();
        }
    }
    static class ThreadBB extends Thread{
        private MyService service;

        public ThreadBB(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.methodB();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadAA aa = new ThreadAA(service);
        aa.setName("AA");
        Thread.sleep(100);
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
        ThreadBB bb = new ThreadBB(service);
        bb.setName("BB");
        bb.start();
    }
}
