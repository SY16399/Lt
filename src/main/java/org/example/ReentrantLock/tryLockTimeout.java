package org.example.ReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class tryLockTimeout {
    static class MyService{
        public ReentrantLock lock = new ReentrantLock();
        public void waitMethod(){
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)){
                    System.out.println("    "+Thread.currentThread().getName()+" 获得锁的时间："+System.currentTimeMillis());
                    Thread.sleep(10000);
                }else {
                    System.out.println("    "+Thread.currentThread().getName()+" 没有获得锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        final MyService service = new MyService();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" 调用waitMethod时间："+System.currentTimeMillis());
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
