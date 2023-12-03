package org.example.Mutilethread.test3;

public class Run4 {
    static class Mythread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                for (int i = 0; i < 100000; i++) {
                    System.out.println("i="+(i+1));
                }
                System.out.println("run begin");
                Thread.sleep(20000000);
                System.out.println("end.....");
            }catch (InterruptedException e) {
                System.out.println("先停止，再遇到了sleep！进入catch了"+this.isInterrupted());
                //throw new RuntimeException(e);
                e.printStackTrace();
            }


        }

    }

    public static void main(String[] args) throws InterruptedException {
        Mythread mythread = new Mythread();
        mythread.start();
        //Thread.sleep(200);
        mythread.interrupt();
        System.out.println("end");
    }
}
