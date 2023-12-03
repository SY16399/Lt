package org.example.Mutilethread.TEST8;

public class Run1 {
    static class Task{
        private String getDatal1;
        private String getDatal2;
        public synchronized void doLongTimeTask(){
            try{
                System.out.println("begin task");
                Thread.sleep(3000);
                getDatal1 = "长时间处理任务后从远程返回的值 1 threadName="+Thread.currentThread().getName();
                getDatal2 = "长时间处理任务后从远程返回的值 2 threadName="+Thread.currentThread().getName();
                System.out.println(getDatal1);
                System.out.println(getDatal2);
                System.out.println("end task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class CommonUtils{
        public static long beginTime1;
        public static long endTime1;
        public static long beginTime2;
        public static long endTime2;
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
            CommonUtils.beginTime1 = System.currentTimeMillis();
            task.doLongTimeTask();
            CommonUtils.endTime1 = System.currentTimeMillis();
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
            CommonUtils.beginTime2 = System.currentTimeMillis();
            task.doLongTimeTask();
            CommonUtils.endTime2 = System.currentTimeMillis();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        MyThread1 thread1 = new MyThread1(task);
        thread1.start();
        MyThread2 thread2 = new MyThread2(task);
        thread2.start();
        Thread.sleep(10000);
        long beginTime = CommonUtils.beginTime1;
        if (CommonUtils.beginTime2 < CommonUtils.beginTime1){
            beginTime = CommonUtils.beginTime2;
        }
        long endTime =CommonUtils.endTime1;
        if (CommonUtils.endTime2 > CommonUtils.endTime1){
            endTime = CommonUtils.endTime2;
        }
        System.out.println("耗时： "+(endTime-beginTime)/1000);
    }
}
