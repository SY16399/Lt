package org.example.extthread;

public class stateTest2 {
    static class MyThread extends Thread{
        @Override
        public void run() {
            try{
                System.out.println("begin sleep");
                Thread.sleep(10000);
                System.out.println("    end sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            MyThread t = new MyThread();
            t.start();
            Thread.sleep(1000);
            System.out.println("main 方法中的状态："+t.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
