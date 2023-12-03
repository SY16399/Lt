package org.example.Join;

public class joinTest {
    static class MyThread extends Thread{
        @Override
        public void run() {
            try{
                int secondValue = (int) (Math.random()*10000);
                System.out.println(secondValue);
                Thread.sleep(secondValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        //Thread.sleep(?)
        System.out.println("main 线程想实现当 thread 对象执行完毕后再继续向下执行 ，");
        System.out.println("但上面代码中的 sleep() 中的值应该写多少呢？");
        System.out.println("答案是：根据不能确定 :)");
    }
}
