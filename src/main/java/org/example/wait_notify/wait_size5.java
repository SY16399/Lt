package org.example.wait_notify;

import java.util.ArrayList;
import java.util.List;

public class wait_size5 {
    static class MyList {
        private static List<String> list = new ArrayList<String>();

        public static void add() {
            list.add("沈阳");
        }

        public static int size() {
            return list.size();
        }
    }

    static class ThreadA extends Thread {
        private Object lock;

        public ThreadA(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                synchronized (lock){
                    if (MyList.size() != 5){
                        System.out.println("wait  begin  "+System.currentTimeMillis());
                        lock.wait();
                        System.out.println("wait  end    "+System.currentTimeMillis());
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    static class ThreadB extends Thread {
        private Object lock;

        public ThreadB(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            try{
                //String ad = new String();
                synchronized (lock){
                    for (int i = 0; i < 10; i++) {
                        MyList.add();
                        if (MyList.size()==5){
                            lock.notify();
                            System.out.println("已发出通知！");
                        }
                        System.out.println("添加了 "+(i+1)+"个元素");
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            Object object = new Object();
            ThreadA a = new ThreadA(object);
            a.setName("A");
            a.start();
            Thread.sleep(50);
            ThreadB b = new ThreadB(object);
            b.setName("B");
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
