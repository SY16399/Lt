package org.example.Mutilethread.Test6;

public class Run {
    static class MyObject{
        synchronized public void methodA(){
            try{
                System.out.println("begin methodA threadName="+Thread.currentThread().getName());
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
            myObject.methodA();
        }
    }
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ThreadA threadA = new ThreadA(myObject);
        threadA.setName("a");
        ThreadB threadB = new ThreadB(myObject);
        threadB.setName("b");
        threadA.start();
        threadB.start();

    }
}
