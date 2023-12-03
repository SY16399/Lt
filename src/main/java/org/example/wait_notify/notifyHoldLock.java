package org.example.wait_notify;

public class notifyHoldLock {
    static class MyService{
        private Object lock = new Object();
        public void waitMethod(){
            try{
                synchronized (lock){
                    System.out.println("begin wait() ThreadNmae="+Thread.currentThread().getName()
                            +" time="+System.currentTimeMillis());
                    lock.wait();
                    System.out.println("  end wait() ThreadName="+Thread.currentThread().getName()
                            +" time="+System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void notifyMethod(){
            try{
                synchronized (lock){
                    System.out.println("begin notify() ThreadName="+Thread.currentThread().getName()+" time="
                            +System.currentTimeMillis());
                    lock.notify();
                    Thread.sleep(5000);
                    System.out.println("  end notify() ThreadName="+Thread.currentThread().getName()
                            +" time="+System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    static class MyThreadA extends Thread{
        private MyService myService;

        public MyThreadA(MyService myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            myService.waitMethod();
        }
    }
    static class MyThreadB extends Thread{
        private MyService myService;

        public MyThreadB(MyService myService) {
            super();
            this.myService = myService;
        }

        @Override
        public void run() {
            myService.notifyMethod();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThreadA a = new MyThreadA(myService);
        a.start();
        Thread.sleep(50);
        MyThreadB b = new MyThreadB(myService);
        b.start();
    }
}
