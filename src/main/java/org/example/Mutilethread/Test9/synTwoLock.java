package org.example.Mutilethread.Test9;

public class synTwoLock {
    static class Service {
        synchronized public static void printA() {
            try {
                System.out.println(" 线程名称为: " + Thread.currentThread().getName()
                        + " 在" + System.currentTimeMillis() + " 进入printA");
                Thread.sleep(3000);
                System.out.println(" 线程名称为：" + Thread.currentThread().getName()
                        + " 在 " + System.currentTimeMillis() + " 离开printA");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public static void printB() {
            System.out.println(" 线程名称为: " + Thread.currentThread().getName()
                    + " 在" + System.currentTimeMillis() + " 进入printB");
            System.out.println(" 线程名称为：" + Thread.currentThread().getName()
                    + " 在 " + System.currentTimeMillis() + " 离开printB");
        }

        synchronized public void printC() {
            System.out.println(" 线程名称为: " + Thread.currentThread().getName()
                    + " 在" + System.currentTimeMillis() + " 进入printC");
            System.out.println(" 线程名称为：" + Thread.currentThread().getName()
                    + " 在 " + System.currentTimeMillis() + " 离开printC");
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
            service.printA();
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
            service.printB();
        }
    }

    static class ThreadC extends Thread {
        private Service service;

        public ThreadC(Service service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.printC();
        }
    }

    public static void main(String[] args) {
       Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");
        threadA.start();
        ThreadB threadB = new ThreadB(service);
        threadB.setName("B");
        threadB.start();
        ThreadC threadC = new ThreadC(service);
        threadC.setName("C");
        threadC.start();
    }
}
