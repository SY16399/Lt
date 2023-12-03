package org.example.Error;
import java.lang.Thread.UncaughtExceptionHandler;
public class Main {
    static class MyThread extends Thread{
        @Override
        public void run() {
            String username= null;
            //NUllPointError
            System.out.println(username.hashCode());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        t1.setName(" 线程     t1");
        t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程："+t.getName()+" 出现了异常：");
                e.printStackTrace();
            }
        });
        t1.start();
        MyThread t2 = new MyThread();
        t2.setName(" 线程 t2");
        Thread.sleep(10);
        t2.start();
    }

}
