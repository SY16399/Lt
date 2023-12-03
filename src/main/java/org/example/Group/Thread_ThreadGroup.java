package org.example.Group;

public class Thread_ThreadGroup {

    static class Task implements Runnable{
        @Override
        public void run() {
            System.out.println("当前线程："+Thread.currentThread().getName()
                    +"   所在的线程组: "+Thread.currentThread().getThreadGroup().getName());
            Thread thread = new Thread(() -> System.out.println("当前线程："+Thread.currentThread().getName()
                    +"   所在的线程组: "+Thread.currentThread().getThreadGroup().getName()));
            thread.start();
        }
    }

    public static void main(String[] args) {
        Task task = new Task();
        ThreadGroup group = new ThreadGroup("子线程组");
        Thread myThread = new Thread(group,task);
        myThread.setName("myThread");
        myThread.start();
    }
}
