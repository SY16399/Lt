package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserConditionWaitNotifyError {
    static class MyService{
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        public void await(){
            try{
                condition.await();//使当前线程在接受到通知或被中断之前，一直处于等待状态。和 wait()方法的作用一样
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
            service.await();
        }
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.start();
    }
}
