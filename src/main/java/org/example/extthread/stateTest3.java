package org.example.extthread;

public class stateTest3 {
    static class MyService{
        synchronized static  public void serviceMethod(){
            try{
                System.out.println(Thread.currentThread().getName()+" 进入业务方法！");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class MyTask implements Runnable {

        @Override
        public void run() {
            MyService.serviceMethod();
        }
    }
    //NEW
    //RUNNABLE
    //TERMINATED
    //BLOCKED
    //WAITING
    //TIME_WAITING
    public static void main(String[] args) throws InterruptedException {
        MyTask task = new MyTask();
        Thread myThread1 = new Thread(task);
        myThread1.setName("a");
        myThread1.start();
        Thread.sleep(1000);
        System.out.println("t1"+myThread1.getState());
        Thread myThread2 = new Thread(task);
        myThread2.setName("b");
        myThread2.start();
        Thread.sleep(1000);
        System.out.println("main 方法中的t2 状态："+myThread2.getState());

    }

}
