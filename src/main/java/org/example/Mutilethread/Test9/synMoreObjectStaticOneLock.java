package org.example.Mutilethread.Test9;

public class synMoreObjectStaticOneLock {
    static class Service {
        public static void printA() {
            synchronized (Service.class) {
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
        }

        synchronized static public void printB() {
            System.out.println(" 线程名称为: " + Thread.currentThread().getName()
                    + " 在" + System.currentTimeMillis() + " 进入printB");
            System.out.println(" 线程名称为：" + Thread.currentThread().getName()
                    + " 在 " + System.currentTimeMillis() + " 离开printB");
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

    public static void main(String[] args) {
        Service service1 = new Service();
        Service service2 = new Service();
        ThreadA threadA = new ThreadA(service1);
        threadA.setName("A");
        threadA.start();
        ThreadB threadB = new ThreadB(service2);
        threadB.setName("B");
        threadB.start();
    }

}
