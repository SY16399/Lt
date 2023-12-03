package org.example.extthread;

public class extthread1 {
    static class MyThread extends Thread {
        public MyThread() {
            System.out.println(" 构造方法中的状态 Thread.currentThread().getState()=" + Thread.currentThread().getState()+
                    "   ThreaName:"+Thread.currentThread().getName());
            System.out.println(" 构造方法中的状态 this.getState()=" + this.getState());
        }

        @Override
        public void run() {
            System.out.println("run 方法中的状态："+Thread.currentThread().getState());
        }
    }
    //NEW
    //RUNNABLE
    //TERMINATED

    //BLOCKED
    //WAITING
    //TIME_WAITING
    public static void main(String[] args) {
        try {
            MyThread t = new MyThread();
            System.out.println("main 方法中的状态 1:" + t.getState());
            Thread.sleep(1000);
            t.start();
            Thread.sleep(1000);
            System.out.println("main 方法中的状态 2:" + t.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
