package org.example.extthread;

public class stateTest4 {
    static class Lock {
        public static final Byte lock = new Byte("0");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                synchronized (Lock.lock) {
                    Lock.lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //NEW
    //RUNNABLE
    //TERMINATED
    //BLOCKED
    //WAITING
    //TIME_WAITING
    public static void main(String[] args) {
        try {
            MyThread t = new MyThread();
            t.start();
            Thread.sleep(1000);
            System.out.println("main 方法中的 t 状态" + t.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
