package org.example.wait_notify;

public class WN {
    static class MyThread1 extends Thread{
        private Object lock;

        public MyThread1(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                synchronized (lock){
                    System.out.println("开始      wait time = "+System.currentTimeMillis());
                    lock.wait();
                    System.out.println("结束      wait time = "+System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class MyThread2 extends Thread{
        private Object lock;

        public MyThread2(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock){
                System.out.println("开始      notify time= "+System.currentTimeMillis());
                lock.notify();
                System.out.println("结束      notify time= "+System.currentTimeMillis());
            }
        }
    }

    public static void main(String[] args) {
        try{
            Object lock = new Object();
            MyThread1 t1 = new MyThread1(lock);
            t1.start();
            Thread.sleep(3000);
            MyThread2 t2 = new MyThread2(lock);
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
