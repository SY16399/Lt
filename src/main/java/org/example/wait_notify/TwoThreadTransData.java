package org.example.wait_notify;

import java.util.ArrayList;
import java.util.List;

public class TwoThreadTransData {
    static class MyList {
        volatile private List<String> list = new ArrayList<String>();//增加在A,B线程间的可见性

        public void add() {
            list.add("沈阳");
        }

        public int size() {
            return list.size();
        }
    }

    static class ThreadA extends Thread {
        private MyList list;

        public ThreadA(MyList list) {
            this.list = list;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    list.add();
                    System.out.println(" 添加了 " + (i + 1) + "个元素");
                    Thread.sleep(1000);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    static class ThreadB extends Thread {
        private MyList list;

        public ThreadB(MyList list) {
            this.list = list;
        }

        @Override
        public void run() {
            try {
                while(true){
                    //Thread.sleep(2000);
                    if (list.size() == 5){
                        System.out.println("==5了，线程b要推出了");
                        throw new InterruptedException();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyList service = new MyList();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
    }
}
