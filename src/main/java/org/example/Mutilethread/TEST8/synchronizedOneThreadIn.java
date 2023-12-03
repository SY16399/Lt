package org.example.Mutilethread.TEST8;

public class synchronizedOneThreadIn {
    static class objectService{
        public void serviceMethod(){
            try{
                synchronized (this){
                    System.out.println("begin  time= "+System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("end    end="+System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadA extends Thread{
        private objectService service;

        public ThreadA(objectService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.serviceMethod();
        }
    }
    static class ThreadB extends Thread{
        private objectService service;

        public ThreadB(objectService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            super.run();
            service.serviceMethod();
        }
    }

    public static void main(String[] args) {
        objectService service = new objectService();
        ThreadA a = new ThreadA(service);
        a.setName("a");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("b");
        b.start();
    }
}
