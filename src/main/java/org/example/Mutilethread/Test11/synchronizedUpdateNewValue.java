package org.example.Mutilethread.Test11;

public class synchronizedUpdateNewValue {
    static class Service{
        private boolean isContinueRun = true;
        public void runMethod(){
            String anyString = new String();
            while(isContinueRun){

            }
            System.out.println("停下来了！");
        }
        public void stopMethod(){
            isContinueRun= false;
        }
    }
    static class ThreadA extends Thread{
        private Service service;

        public ThreadA(Service service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.runMethod();
        }
    }
    static class ThreadB extends Thread{
        private Service service;

        public ThreadB(Service service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.stopMethod();
        }
    }

    public static void main(String[] args) {
        try{
            Service service = new Service();
            ThreadA threadA = new ThreadA(service);
            threadA.start();
            Thread.sleep(1000);
            ThreadB threadB =new ThreadB(service);
            threadB.start();
            System.out.println("已经发起了停止的命令");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
