package org.example.Mutilethread.Test6;

public class RunT {
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
    }
    static class ThreadA extends Thread{
        private Run.MyObject myObject;

        public ThreadA(Run.MyObject myObject) {
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
        private Run.MyObject myObject;

        public ThreadB(Run.MyObject myObject) {
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
        Run.MyObject myObject = new Run.MyObject();
        Run.ThreadA threadA = new Run.ThreadA(myObject);
        threadA.setName("a");
        Run.ThreadB threadB = new Run.ThreadB(myObject);
        threadB.setName("b");
        threadA.start();
        threadB.start();

    }
}
