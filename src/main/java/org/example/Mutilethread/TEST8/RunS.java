package org.example.Mutilethread.TEST8;

public class RunS {
    static class Service{
        private String usernameParam;
        private String passwordParam;
        String anyString = new String();
        public void setUsernamePassword(String usernameParam,String passwordParam){
            try {

                synchronized (anyString){
                    System.out.println(" 线程名称为："+Thread.currentThread().getName()
                    +" 在 "+System.currentTimeMillis()+" 进入同步块 ");
                    this.usernameParam = usernameParam;
                    Thread.sleep(3000);//模拟线程处理需要的耗时
                    this.passwordParam = passwordParam;
                    System.out.println(" 线程名称为："+
                            Thread.currentThread().getName()+" 在 "+System.currentTimeMillis()+" 离开同步块");
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
            service.setUsernamePassword("a","aa");
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
            service.setUsernamePassword("b","bb");
        }
    }

    public static void main(String[] args) {
        Service service = new Service();
        ThreadA  a = new ThreadA(service);
        a.setName("A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("B");
        b.start();
    }
}
