package org.example.Mutilethread.Test7;

public class RunK {
    static class Main{
        public int i = 10;
        synchronized public void operateMainMethod(){
            try {
                i--;
                System.out.println(getClass()+" "+ this);
                System.out.println(" mian print i= " + i);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Sub extends Main{
        synchronized public void operateISubMehod() {
            try {
                while (i > 0) {
                    i--;
                    System.out.println(getClass()+" "+this);
                    System.out.println(" sub print i= " + i);
                    Thread.sleep(100);
                    super.operateMainMethod();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class MyThread extends Thread {
        Sub sub = new Sub();
        @Override
        public void run() {
            sub.operateISubMehod();
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
