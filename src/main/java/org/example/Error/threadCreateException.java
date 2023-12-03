package org.example.Error;

public class threadCreateException {
    static class MyThread extends Thread{
        @Override
        public void run() {
            String username= null;
            //NUllPointError
            System.out.println(username.hashCode());
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}
