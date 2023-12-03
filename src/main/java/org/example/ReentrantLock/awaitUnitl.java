package org.example.ReentrantLock;

import java.util.Calendar;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class awaitUnitl {
    static class MyThreadA extends Thread{
        private Service service;

        public MyThreadA(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.waitMethod();
        }
    }
    static class MyThreadB extends Thread{
        private Service service;

        public MyThreadB(Service service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.notifyMethod();
        }
    }

    private static class Service {
        private ReentrantLock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        public void waitMethod() {
            try {
                //操作日期的类
                Calendar calendarRef = Calendar.getInstance();
                calendarRef.add(Calendar.SECOND,10);//当前日期加10s
                lock.lock();
                System.out.println("wait begin timer="+System.currentTimeMillis());
                condition.awaitUntil(calendarRef.getTime());
                System.out.println("wait    end timer="+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void notifyMethod() {
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND,10);
                lock.lock();
                System.out.println("notify begin timer="+System.currentTimeMillis());
                condition.signalAll();
                System.out.println("notify  end timer="+System.currentTimeMillis());
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        MyThreadA myThreadA = new MyThreadA(service);
        myThreadA.start();
        Thread.sleep(2000);
        MyThreadB threadB = new MyThreadB(service);
        threadB.start();
    }
}
