package org.example.Mutilethread.Test10;

public class deadLock {
    static class DealThread implements Runnable{
        public String username;
        public final Object lock1 = new Object();
        public final Object lock2 = new Object();
        public void  setFlag(String username){
            this.username = username;
        }

        @Override
        public void run() {
            if (username.equals("a")){
                synchronized (lock1){
                    try{
                        System.out.println("username= "+username);
                        Thread.sleep(3000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2){
                        System.out.println("按 lock1->lock2代码顺序执行了 ");
                    }
                }
            }
            if (username.equals("b")){
                synchronized (lock2){
                    try{
                        System.out.println("username= "+username);
                        Thread.sleep(3000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1){
                        System.out.println("按 lock1->lock2代码顺序执行了 ");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            DealThread t1 = new DealThread();
            t1.setFlag("a");
            Thread thread1 = new Thread(t1);
            thread1.start();

            Thread.sleep(100);

            t1.setFlag("b");
            Thread thread2 = new Thread(t1);
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
