package org.example.Mutilethread.Test5;

public class RunP {
    static class MyThread extends Thread{
        private long i = 0;

        public long getI() {
            return i;
        }

        @Override
        public void run() {
            while (true){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread a = new MyThread();
        a.setPriority(Thread.NORM_PRIORITY-3);
        a.start();
        MyThread b = new MyThread();
        b.setPriority(Thread.NORM_PRIORITY+3);
        b.start();
        Thread.sleep(20000);
        a.stop();
        b.stop();
        System.out.println("a="+a.getI());
        System.out.println("b="+b.getI());

    }
}
