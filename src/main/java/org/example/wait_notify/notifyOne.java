package org.example.wait_notify;

public class notifyOne {
    static class MyService {
        private Object lock = new Object();

        public void waitMethod() {
            try {
                synchronized (lock) {
                    System.out.println("begin wait " + System.currentTimeMillis() +
                            " " + Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("    end wait " + System.currentTimeMillis() +
                            " " + Thread.currentThread().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void notifyMethod() {
            synchronized (lock) {
                System.out.println("begin notify " + System.currentTimeMillis() +
                        " " + Thread.currentThread().getName());
                lock.notify();
                System.out.println("    end notify " + System.currentTimeMillis() +
                        " " + Thread.currentThread().getName());
            }
        }
    }
    //执行wait 方法的线程
    static class MyThreadA extends Thread{
        private MyService service;

        public MyThreadA(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.waitMethod();
        }
    }
    //创建唤醒线程如下
    static class MyThreadB extends Thread{
        private MyService service;

        public MyThreadB(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.notifyMethod();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        for (int i = 0; i < 10; i++) {
            MyThreadA t1 = new MyThreadA(service);
            t1.start();
        }
        Thread.sleep(1000);
        MyThreadB t1 = new MyThreadB(service);
        t1.start();
        Thread.sleep(500);
        MyThreadB t2 = new MyThreadB(service);
        t2.start();
        Thread.sleep(500);
        MyThreadB t3 = new MyThreadB(service);
        t3.start();
        Thread.sleep(500);
        MyThreadB t4 = new MyThreadB(service);
        t4.start();
        Thread.sleep(500);
        MyThreadB t5 = new MyThreadB(service);
        t5.start();
        //一共唤醒5个线程
    }
}
