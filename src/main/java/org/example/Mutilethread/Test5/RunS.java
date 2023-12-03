package org.example.Mutilethread.Test5;

import java.util.concurrent.locks.LockSupport;

public class RunS {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println("begin+" +System.currentTimeMillis());
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LockSupport.park();
            System.out.println("end+"+ System.currentTimeMillis());
        }

    }

    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(1000);
        LockSupport.unpark(thread);
    }
}
