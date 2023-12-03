package org.example.Mutilethread.Test5;

public class RunY {
    static class MyThread extends Thread{

        @Override
        public void run() {
            long beginTime = System.currentTimeMillis();
            int count = 0;
            for (int i = 0; i < 5000000; i++) {
                //Thread.yield();
                count = count+(i+1);
            }
            long end = System.currentTimeMillis();
            System.out.println("用时="+(end-beginTime)+"毫秒");
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.setPriority(10);
        thread.start();
    }
}
