package org.example.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class lockInterruptibly {
    static class MyService{
        private ReentrantLock lock = new ReentrantLock();
        public void testMethod() throws InterruptedException {
            lock.lockInterruptibly();
            System.out.println("begin "+Thread.currentThread().getName()+" "+System.currentTimeMillis());
            for (int i = 0; i < Integer.MAX_VALUE / 10; i++) {
                String newString = new String();
                Math.random();
                //为了不让 currentThread() 方法占有过多的CPU资源
                //执行yield() 方法
                Thread.yield();
            }
            System.out.println(" end "+Thread.currentThread().getName()+" "+System.currentTimeMillis());
            lock.unlock();
        }
    }
    static class ThreadA extends Thread{
        private MyService service;

        public ThreadA(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            try {
                service.testMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        Thread.sleep(500);
        ThreadA b = new ThreadA(service);
        b.setName("b");
        b.start();
        Thread.sleep(500);
        b.interrupt();
        System.out.println("main 线程尝试中断线程b,但没有成功");
    }

}
