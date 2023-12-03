package org.example.Mutilethread.Test4;

public class Run4 {
    static class Mythread extends Thread{
        @Override
        public void run() {
            try {
                for (int i = 0; i < 5000000; i++) {
                    System.out.println(i+1);
                }
            } catch (ThreadDeath e) {
                e.printStackTrace();
                System.out.println("进入了Mythread中的catch代码块中");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
       Mythread thread = new Mythread();
       thread.start();
       Thread.sleep(1000);
        try {
            thread.stop();
        } catch (ThreadDeath e) {
            System.out.println("进入了Run4类 main中的catch代码块中");
            e.printStackTrace();
        }
    }
}
