package org.example.Mutilethread.Test9;

public class Run1 {
    static class MyObject {
        public void speedPrintString() {
            synchronized (this) {
                System.out.println("speedPrintString ________________getLock time=" + System.currentTimeMillis() + "run ThreadName=" + Thread.currentThread().getName());
                System.out.println("--------------------------");
                System.out.println("speedPrintString releaseLock time=" + System.currentTimeMillis() + "run ThreadName=" + Thread.currentThread().getName());
            }
        }
    }

    static class Service {
        public void testMethod1(MyObject object) {
            synchronized (object) {
                try {
                    System.out.println("testMethod1 ________________getLock time=" + System.currentTimeMillis() + "run ThreadName=" + Thread.currentThread().getName());
                    Thread.sleep(5000);
                    System.out.println("testMethod1 releaseLock time=" + System.currentTimeMillis() + "run ThreadName=" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class ThreadA extends Thread {
        private Service service;
        private MyObject myObject;

        public ThreadA(Service service, MyObject myObject) {
            super();
            this.service = service;
            this.myObject = myObject;
        }

        @Override
        public void run() {
            super.run();
            service.testMethod1(myObject);
        }
    }

    static class ThreadB extends Thread {
        private MyObject myObject;

        public ThreadB(MyObject myObject) {
            super();
            this.myObject = myObject;
        }

        @Override
        public void run() {
            super.run();
            myObject.speedPrintString();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        MyObject object = new MyObject();
        ThreadA threadA = new ThreadA(service, object);
        threadA.setName("A");
        threadA.start();
        Thread.sleep(100);
        ThreadB threadB = new ThreadB(object);
        threadB.setName("B");
        threadB.start();
    }
}
