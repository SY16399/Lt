package org.example.Mutilethread.TEST2;

public class MyThread extends Thread{
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            if (this.isInterrupted()){
                System.out.println("已经是停止状态了，退出！");
                break;
            }
            System.out.println("i="+(i+1));
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(20000);
            thread.interrupt();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end......");
    }
}
