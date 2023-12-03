package org.example.Error;

public class thread_Group1 {
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
                System.out.println("死循环中: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("我得线程组");
        MyThread[] myThreads = new MyThread[10];
        for (int i = 0; i < myThreads.length; i++) {
            myThreads[i] = new MyThread(group,"线程 "+(i+1),"1");
            myThreads[i].start();
        }
        MyThread newT = new MyThread(group,"报错线程 ","a");
        newT.start();

    }
}
