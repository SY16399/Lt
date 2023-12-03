package org.example.Mutilethread.Test9;

public class Run {
    static class MyObject{}
    static class Service{
        public void testMethod1(MyObject object){
            synchronized (object){
                try{
                    System.out.println("testMethod1 ________________getLock time="+System.currentTimeMillis()+"run ThreadName="+Thread.currentThread().getName());
                    Thread.sleep(2000);
                    System.out.println("testMethod1 releaseLock time="+System.currentTimeMillis()+"run ThreadName="+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    static class ThreadA extends Thread{
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
    static class ThreadB extends Thread{
        private Service service;
        private MyObject myObject;

        public ThreadB(Service service, MyObject myObject) {
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

    public static void main(String[] args) {
        Service service = new Service();
        MyObject object = new MyObject();
        MyObject object2 = new MyObject();
        ThreadA threadA = new ThreadA(service,object);
        threadA.setName("A");
        threadA.start();
        ThreadB threadB = new ThreadB(service,object2);
        threadB.setName("B");
        threadB.start();
    }
}
