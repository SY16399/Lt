package org.example.Mutilethread.Test4;

public class Run {
    static class Mythread extends Thread{
        private int i = 0;
        @Override
        public void run() {
            try {
               Thread.sleep(2000);
               int i =100;
                System.out.println("begin");
                if (i==100){
                    this.stop();
                }
                System.out.println("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Mythread thread = new Mythread();
        thread.start();
    }
}
