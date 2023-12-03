package org.example.Mutilethread.Test5;

public class RunF {
    static class MyThread extends Thread{
        private long i = 0;

        public long getI() {
            return i;
        }

        public void setI(long i) {
            this.i = i;
        }

        @Override
        public void run() {
            while (true){
                i++;
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(10);
            thread.suspend();
            System.out.println("main end ===========================================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
