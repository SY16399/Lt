package org.example.Join;

public class joinTest2 {
    public static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                int secondValue = (int) (Math.random() * 10000);
                System.out.println(secondValue);
                Thread.sleep(secondValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try{
            MyThread thread = new MyThread();
            thread.start();
            thread.join();
            System.out.println("等待thread对象执行后再执行.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

