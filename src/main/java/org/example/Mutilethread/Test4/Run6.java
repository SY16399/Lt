package org.example.Mutilethread.Test4;

public class Run6 {
    static class Mythread extends Thread{
        private int i = 0;
        @Override
        public void run() {
            while (true){
                if (this.isInterrupted()){
                    return;
                }
                System.out.println("tinme = "+System.currentTimeMillis());
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        Mythread thread = new Mythread();
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }
}
