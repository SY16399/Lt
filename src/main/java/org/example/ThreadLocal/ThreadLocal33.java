package org.example.ThreadLocal;

import java.util.Date;

public class ThreadLocal33 {
    static class ThreadLocalExt extends ThreadLocal{
        @Override
        protected Object initialValue() {
            return new Date().getTime();
        }
    }
    static class Tools{
        public static ThreadLocalExt t1 = new ThreadLocalExt();
    }
    static class ThreadA extends Thread{
        @Override
        public void run() {
            try{
                for (int i = 0; i < 10; i++) {
                    System.out.println("在ThreadA线程中取值="+Tools.t1.get());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("    在main线程中取值="+Tools.t1.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA threadA = new ThreadA();
            threadA.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
