package org.example.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class tryLock {
    static class MyService{
        public ReentrantLock lock = new ReentrantLock();
        public void waitMethod(){
            if (lock.tryLock()){
                System.out.println(Thread.currentThread().getName()+" 获得锁");
            }else {
                System.out.println(Thread.currentThread().getName()+" 没有获得锁");
            }
        }
    }

    public static void main(String[] args) {
        final MyService service = new MyService();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };
        Thread threadA = new Thread(runnable);
        threadA.setName("A");
        threadA.start();
        Thread threadB = new Thread(runnable);
        threadB.setName("B");
        threadB.start();
    }
}
