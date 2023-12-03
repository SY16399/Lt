package org.example.Error;

public class threadGroup {
    static class MyThreadGroup extends ThreadGroup {

        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            super.uncaughtException(t, e);
            this.interrupt();
        }
    }

    static class MyThread extends Thread {
        private String num;

        public MyThread(ThreadGroup group, String name, String num) {
            super(group, name);
            this.num = num;
        }

        @Override
        public void run() {
            int numInt = Integer.parseInt(num);
            while (true) {
                if (!this.isInterrupted()){
                    System.out.println("死循环中: " + Thread.currentThread().getName());
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadGroup group = new MyThreadGroup("我的线程组");
        MyThread[] myThreads = new MyThread[10];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread(group," 线程 "+(i+1),"1");
            myThreads[i].start();
        }
        MyThread newT = new MyThread(group,"报错线程","a");
        newT.start();
    }

}
