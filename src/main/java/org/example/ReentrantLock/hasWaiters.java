package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class hasWaiters {
    static class Service{
        private ReentrantLock lock = new ReentrantLock();
        private Condition newCondition = lock.newCondition();
        public void waitMethod(){
            try{
                lock.lock();
                newCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void notifyMethod(){
            try{
                lock.lock();
                System.out.println("有没有线程正在等待newCondition？"+lock.hasWaiters(newCondition)+" 线程数是多少"+lock.getWaitQueueLength(newCondition));
                newCondition.signalAll();
            }finally {
                lock.unlock();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            final Service service = new Service();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    service.waitMethod();
                }
            };
            Thread[] threads = new Thread[10];
            for (int i = 0; i < 10; i++) {
                threads[i] = new Thread(runnable);
            }
            for (int i = 0; i < 10; i++) {
                threads[i].start();
            }
            Thread.sleep(2000);
            service.notifyMethod();
        }
    }
}
