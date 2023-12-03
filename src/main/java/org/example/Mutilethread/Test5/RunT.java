package org.example.Mutilethread.Test5;

public class RunT {
    static class Synchronized{
        synchronized public void printString(){
            System.out.println("begin");
            if (Thread.currentThread().getName().equals("a")){
                System.out.println("a线程永远 suspend了");
                Thread.currentThread().suspend();
            }
            System.out.println("end");
        }
    }

    public static void main(String[] args) {
        try {
            final Synchronized obj = new Synchronized();
            Thread thread1 = new Thread(){
                @Override
                public void run() {
                    obj.printString();
                }
            };
            thread1.setName("a");
            thread1.start();
            Thread.sleep(1000);
            Thread thread2 = new Thread(){
                @Override
                public void run() {
                    System.out.println("thread2启动了但进入不了printString方法只打印一个begin");
                    System.out.println("因为printString方法被a线程永远的锁定并suspend暂停了");
                    obj.printString();
                }
            };
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
