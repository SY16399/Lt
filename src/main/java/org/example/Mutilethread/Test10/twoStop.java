package org.example.Mutilethread.Test10;

public class twoStop {
    static class Service {
        Object o1 = new Object();

        public void MethodA() {
            synchronized (o1) {
                System.out.println("methodA begin");
                boolean isContinueRun = true;
                while (isContinueRun) {
                }
                System.out.println("methodA end");
            }
        }

        Object o2 = new Object();

        public void MethodB() {
            synchronized (o2) {
                System.out.println("MethodB begin");
                System.out.println("MethodB end");
            }
        }
    }

    static class ThreadA extends Thread {
        private Service service;

        public ThreadA(Service service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.MethodA();
        }
    }

    static class ThreadB extends Thread {
        private Service service;

        public ThreadB(Service service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.MethodB();
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.start();
        ThreadB threadB = new ThreadB(service);
        threadB.start();
    }
}
