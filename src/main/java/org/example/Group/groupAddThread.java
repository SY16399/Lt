package org.example.Group;


public class groupAddThread {
    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("ThreadName" + Thread
                            .currentThread()
                            .getName());
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Task task = new Task();
        ThreadGroup group = new ThreadGroup("shenyang的线程组");
        Thread athread = new Thread(group, task);
        Thread bthread = new Thread(group, task);
        athread.start();
        bthread.start();
        System.out.println("活动的线程数为：" + group.activeCount());
        System.out.println("线程组的名称为：" + group.getName());
    }
}
