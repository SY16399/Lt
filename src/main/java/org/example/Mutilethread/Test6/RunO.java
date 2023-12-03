package org.example.Mutilethread.Test6;

public class RunO {
    static class MyObject{
        synchronized public void methodA(){
            try{
                System.out.println("begin methodA threadName="+Thread.currentThread().getName());
                Thread.sleep(5000);
                System.out.println("end endTime="+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized public void methodB(){
            try{
                System.out.println("begin methodB threadName="+Thread.currentThread().getName()+" begin time="+System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadA extends Thread{
        private MyObject myObject;

        public ThreadA(MyObject myObject) {
            super();
            this.myObject = myObject;
        }

        @Override
        public void run() {
            super.run();
            myObject.methodA();
        }
    }
    static class ThreadB extends Thread{
        private MyObject myObject;

        public ThreadB(MyObject myObject) {
            super();
            this.myObject = myObject;
        }

        @Override
        public void run() {
            super.run();
            myObject.methodB();
        }
    }
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ThreadA threadA = new ThreadA(myObject);
        threadA.setName("A");
        ThreadB threadB = new ThreadB(myObject);
        threadB.setName("B");
        threadA.start();
        threadB.start();

    }
}
