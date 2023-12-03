package org.example.ThreadLocal;

public class ThreadLoalTest {
    static class Tools{
        public static ThreadLocal t1 = new ThreadLocal();
    }
    static class MyThreadA extends Thread{
        @Override
        public void run() {
            try{
                for (int i = 0; i < 10; i++) {
                    Tools.t1.set("A "+(i+1));
                    System.out.println("A get "+Tools.t1.get());
                    int sleepValue = (int) (Math.random()*1000);
                    Thread.sleep(sleepValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class MyThreadB extends Thread{
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Tools.t1.set("B "+(i+1));
                    System.out.println("    B get "+Tools.t1.get());
                    int sleepValue = (int) (Math.random()*1000);
                    Thread.sleep(sleepValue);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadA a = new MyThreadA();
        MyThreadB b = new MyThreadB();
        a.start();
        b.start();
        for (int i = 0; i < 10; i++) {
            Tools.t1.set("main "+(i+1));
            System.out.println("        main get "+Tools.t1.get());
            int sleepValue = (int)(Math.random()*1000);
            Thread.sleep(sleepValue);
        }
    }
}
