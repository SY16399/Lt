package org.example.Mutilethread.Test10;

public class setNewStringTwoLock {
    static class NyService{
        private String key = "123";
        public void  testMethod(){
            try{
                synchronized (key){
                    System.out.println(Thread.currentThread().getName()+" begein "+System.currentTimeMillis());
                    key = "456";
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+"  end "+System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadA extends  Thread{
        private NyService service;

        public ThreadA(NyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }
    static class ThreadB extends  Thread{
        private NyService service;

        public ThreadB(NyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NyService service = new NyService();
        ThreadA a = new ThreadA(service);
        a.setName("A");
        ThreadB b = new ThreadB(service);
        b.setName("B");
        a.start();
        b.start();
    }
}
