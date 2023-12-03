package org.example.Mutilethread.test3;

public class Run3 {
    static class Mythread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                System.out.println("run begin");
                Thread.sleep(20000000);
                System.out.println("end.....");
            }catch (InterruptedException e) {
                System.out.println("在沉睡中停止进入run中的catch了"+this.isInterrupted());
                //throw new RuntimeException(e);
                e.printStackTrace();
            }


        }

    }

    public static void main(String[] args) throws InterruptedException {
        Mythread mythread = new Mythread();
        mythread.start();
        Thread.sleep(200);
        mythread.interrupt();
        System.out.println("end");
    }
}
