package org.example.wait_notify;
import java.util.*;
public class wait_notify_Service {
    static class MyList {
        volatile private List<String> list = new ArrayList<>();

        public  void add() {
            list.add("anyString");
        }

        public  int size() {
            return list.size();
        }
    }
    static class MyService{
        private Object lock = new Object();
        private MyList list = new MyList();
        public void waitMethod(){
            try{
                synchronized (lock){
                    if (list.size() != 5){
                        System.out.println(" begin wait "+System.currentTimeMillis()+" "+Thread.currentThread().getName());
                        lock.wait();
                        System.out.println(" end wait "+System.currentTimeMillis()+" "+Thread.currentThread().getName());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void notifyMethod(){
            try{
                synchronized (lock){
                    System.out.println(" begin notify "+System.currentTimeMillis()+" "+Thread.currentThread().getName());
                    for (int i = 0; i < 10; i++) {
                        list.add();
                        if (list.size() == 5){
                            lock.notify();
                            System.out.println(" 仅仅是发出通知而已，wait后面的代码并没有立即执行，因为锁没有释放");
                        }
                        System.out.println("add次数："+(i+1));
                        Thread.sleep(1000);
                    }
                    System.out.println(" end notify "+System.currentTimeMillis()+" "+Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadA extends Thread {
        private MyService service;

        public ThreadA(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.waitMethod();
        }
    }
    static class ThreadB extends Thread {
        private MyService service;

        public ThreadB(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.notifyMethod();
        }
    }

    public static void main(String[] args) {
        try {
            MyService service = new MyService();
            ThreadA a = new ThreadA(service);
            a.setName("A");
            a.start();
            Thread.sleep(5000);
            ThreadB b = new ThreadB(service);
            b.setName("B");
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
