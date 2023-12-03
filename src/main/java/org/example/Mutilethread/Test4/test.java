package org.example.Mutilethread.Test4;

public class test {
    static class Mythread extends Thread{
        private int i = 0;
        @Override
        public void run() {
            try {
                while (true){
                    i++;
                    System.out.println("i="+i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        try {
            Mythread mythread = new Mythread();
            mythread.start();
            Thread.sleep(8000);
            mythread.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
