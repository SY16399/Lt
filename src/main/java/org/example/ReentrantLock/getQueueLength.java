package org.example.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class getQueueLength {
    static class Service {
        public ReentrantLock lock = new ReentrantLock();

        public void serviceMethod() {
            try {
                lock.lock();
                System.out.println("ThreadName=" + Thread.currentThread().getName() + "进入方法！");
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
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
        System.out.println("有线程数："+service.lock.getQueueLength()+"在等待获取锁！");
    }
}
