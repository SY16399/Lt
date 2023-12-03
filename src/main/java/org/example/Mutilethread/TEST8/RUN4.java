package org.example.Mutilethread.TEST8;

public class RUN4 {
    static class ObjectService{
        public void serviceMethodA(){
            try{
                synchronized (this){
                    System.out.println("A begin time="+System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("A end   end="+System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void serviceMethodB(){
            synchronized (this) {
                System.out.println("B begin time="+System.currentTimeMillis());
                System.out.println("B end   end="+System.currentTimeMillis());
            }
        }
    }
    static class ThreadA extends Thread{
        private ObjectService service;

        public ThreadA(ObjectService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.serviceMethodA();
        }
    }
    static class ThreadB extends Thread{
        private ObjectService service;

        public ThreadB(ObjectService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.serviceMethodB();
        }
    }

    public static void main(String[] args) {
        ObjectService objectService = new ObjectService();
        ThreadA threadA = new ThreadA(objectService);
        threadA.setName("a");
        threadA.start();
        ThreadB threadB = new ThreadB(objectService);
        threadB.setName("b");
        threadB.start();

    }
}
