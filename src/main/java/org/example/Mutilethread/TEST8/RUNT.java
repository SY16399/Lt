package org.example.Mutilethread.TEST8;

public class RUNT {
    static class Task {
        synchronized public void otherMethod() {
            System.out.println("=============================run--ortherMethod");
        }

        public void doLongTimeTask() {
            synchronized (this) {
                for (int i = 0; i < 10000; i++) {
                    System.out.println("synchronized threadName=" + Thread.currentThread().getName() + " i=" + (i + 1));
                }
            }
        }
    }
    static class MyThread1 extends Thread{
        private Task task;

        public MyThread1(Task task) {
            super();
            this.task = task;
        }

        @Override
        public void run() {
            super.run();
            task.doLongTimeTask();
        }
    }
    static class MyThread2 extends Thread{
        private Task task;

        public MyThread2(Task task) {
            super();
            this.task = task;
        }

        @Override
        public void run() {
            super.run();
            task.otherMethod();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        MyThread1 thread1 = new MyThread1(task);
        thread1.start();
        Thread.sleep(10);
        MyThread2 thread2 = new MyThread2(task);
        thread2.start();

    }
}
