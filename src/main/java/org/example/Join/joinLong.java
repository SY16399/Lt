package org.example.Join;

public class joinLong {
    static class MyThread extends Thread{
        @Override
        public void run() {
            try{
                System.out.println("run begin Timer ="+System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("run   end Timer="+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try{
            MyThread thread = new MyThread();
            thread.start();
            System.out.println("   main begin time="+System.currentTimeMillis());
            thread.join(8000);//等8s 大于5s
            System.out.println("    main    end time="+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
