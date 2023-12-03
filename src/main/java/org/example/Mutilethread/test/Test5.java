package org.example.Mutilethread.test;

public class Test5 {
    static class MyThread implements Runnable{
        @Override
        public void run() {
            System.out.println("begin "+" "+System.currentTimeMillis());
        }
    }
    public static void main(String[] args) {
        Runnable runnable = new MyThread();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
