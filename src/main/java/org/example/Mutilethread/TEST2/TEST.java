package org.example.Mutilethread.TEST2;

public class TEST {
    static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100000; i++) {
                System.out.println("i=" +(i+1));
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(3000);
        myThread.interrupt();
        //Thread.currentThread().interrupt();
        System.out.println("是否停止1？="+myThread.isInterrupted());
        System.out.println("是否停止2？="+myThread.isInterrupted());

        System.out.println("zzzzzzzzzzzzzzzzzzzzz");
    }
}
