package org.example.Mutilethread.Test10;

public class StringAndSyn {
    static class Service{
        public static void print(String stringParam){
            try{
                synchronized (stringParam){
                    while(true){
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
            service.print(new String());
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
            service.print(new String());
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
