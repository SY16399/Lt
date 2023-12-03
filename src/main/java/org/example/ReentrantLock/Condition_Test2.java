package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Condition_Test2 {
    static class MyService{
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private boolean hasValue = false;
        public void set(){
            try{
                lock.lock();
                while (hasValue){
                    System.out.println("有可能★★连续");
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
                while (!hasValue){
                    System.out.println("有可能☆☆连续");
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
        ThreadA[] a = new ThreadA[10]; ThreadB[] b = new ThreadB[10];
        for (int i = 0; i < 10; i++) {
            a[i] = new ThreadA(service);
            b[i] = new ThreadB(service);
            a[i].start();
            b[i].start();
        }

    }
}
