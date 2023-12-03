package org.example.Error;

import java.lang.Thread.UncaughtExceptionHandler;

public class threadExceptionMove {
    static class MyThread extends Thread {
        private String num = "a";

        public MyThread() {
        }

        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            int numInt = Integer.parseInt(num);
            System.out.println(" 在线程中打印: " + (numInt + 1));
        }
    }

    static class MyThreadGroup extends ThreadGroup {
        public MyThreadGroup(String name) {
            super(name);
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            super.uncaughtException(t, e);
            System.out.println("线程组的异常处理");
            e.printStackTrace();
        }
    }

    static class ObjectUncaughtExceptionHandler implements UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(" 对象的异常处理 ");
            e.printStackTrace();
        }
    }

    static class StateUncaughtExceptionHandler implements UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("静态的异常处理");
            e.printStackTrace();
        }
    }

    static class Run2 {
        public static void main(String[] args) {
            MyThreadGroup myThreadGroup = new MyThreadGroup("我的线程组");
            MyThread myThread = new MyThread(myThreadGroup, "我的线程");
            //对象
            //myThread
            //        .setUncaughtExceptionHandler(new ObjectUncaughtExceptionHandler());
            //类
            //MyThread.
            //        setDefaultUncaughtExceptionHandler(new StateUncaughtExceptionHandler());
            myThread.start();
        }
    }
}
