package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Condition_Test {
    static class MyService{
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private boolean hasValue = false;
        public void set(){
            try{
                lock.lock();
                if (hasValue){
                    condition.await();
                }
                System.out.println("打印★");
                hasValue = true;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void get(){
            try{
                lock.lock();
                if (!hasValue){
                    condition.await();
                }
                System.out.println("打印☆");
                hasValue = false;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
    static class ThreadA extends  Thread{
        private MyService myService;

        public ThreadA(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i <Integer.MAX_VALUE; i++) {
                myService.set();
            }
        }
    }
    static class ThreadB extends  Thread{
        private MyService myService;

        public ThreadB(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i <Integer.MAX_VALUE; i++) {
                myService.get();
            }
        }
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.start();
        ThreadB b = new ThreadB(service);
        b.start();
    }
}
