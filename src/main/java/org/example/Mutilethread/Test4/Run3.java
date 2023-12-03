package org.example.Mutilethread.Test4;

public class Run3 {
    static class Mythread extends Thread{
        @Override
        public void run() {
            try {
                this.stop();
            } catch (ThreadDeath e) {
                System.out.println("进入了catch方法！");
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Mythread thread = new Mythread();
        thread.start();
    }
}
