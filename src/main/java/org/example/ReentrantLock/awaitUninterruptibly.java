package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class awaitUninterruptibly {
    static class Service{
        private final Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        public void testMethod(){
            try {
                lock.lock();
                System.out.println("wait begin");
                condition.awaitUninterruptibly();
                System.out.println("wait    end");
            } finally {
                lock.unlock();
            }
        }
    }
    static class MyThread extends Thread{
        private Service service;

        public MyThread(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    public static void main(String[] args) {
        try{
            Service service = new Service();
            MyThread myThread = new MyThread(service);
            myThread.start();
            Thread.sleep(3000);
            myThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
