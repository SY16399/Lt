package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class z3_ok {
    static class MyService {
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        public void waitMethod(){
            try{
                lock.lock();
                System.out.println("A");
                condition.await();
                System.out.println("B");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
                System.out.println("锁释放了");
            }
        }
    }
    static class MyThreadA extends Thread{
        private MyService service;

        public MyThreadA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.waitMethod();
        }
    }

    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThreadA a1 = new MyThreadA(myService);
        a1.start();
        MyThreadA a2 = new MyThreadA(myService);
        a2.start();
        MyThreadA a3 = new MyThreadA(myService);
        a3.start();
    }
}
