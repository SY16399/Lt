package org.example.wait_notify;

public class notifyAll {
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

        public void notifyAllMethod() {
            synchronized (lock) {
                System.out.println("begin notify " + System.currentTimeMillis() +
                        " " + Thread.currentThread().getName());
                lock.notifyAll();
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
            service.notifyAllMethod();
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
        //一共唤醒5个线程
    }
}
