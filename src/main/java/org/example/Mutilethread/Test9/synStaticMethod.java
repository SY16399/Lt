package org.example.Mutilethread.Test9;

public class synStaticMethod {
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
    }
    static class ThreadA extends Thread{
        @Override
        public void run() {
            Service.printA();
        }
    }
    static class ThreadB extends Thread{
        @Override
        public void run() {
            Service.printB();
        }
    }

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        threadA.setName("A");
        threadA.start();
        ThreadB threadB = new ThreadB();
        threadB.setName("B");
        threadB.start();
    }
}
