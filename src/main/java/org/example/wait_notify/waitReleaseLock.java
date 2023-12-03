package org.example.wait_notify;

public class waitReleaseLock {
    static class Service{
        public void testMethod(Object lock){
            try {
                synchronized (lock){
                    System.out.println("begin wait()");
                    Thread.sleep(40000);
                    System.out.println("end wait()");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadA extends Thread{
        private Object lock;

        public ThreadA(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            Service service = new Service();
            service.testMethod(lock);
        }
    }
    static class ThreadB extends Thread{
        private Object lock;

        public ThreadB(Object lock) {
            super();
            this.lock = lock;
        }

        @Override
        public void run() {
            Service service = new Service();
            service.testMethod(lock);
        }
    }

    public static void main(String[] args) {
        Object lock = new Object();
        ThreadA a = new ThreadA(lock);
        a.start();
        ThreadB b = new ThreadB(lock);
        b.start();
    }
}
