package org.example.Collection;


import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;

public class Concurrent_HashMap {
    static class MyService{
            public ConcurrentHashMap map = new ConcurrentHashMap();
        public void testMethod(){
            for (int i = 0; i < 50000; i++) {
                try {
                    if (!Thread.currentThread().isInterrupted()) {
                        map.put(Thread.currentThread().getName()+" "+(i+1),Thread.currentThread().getName()+" "+(i+1));
                        System.out.println(Thread.currentThread().getName()+" "+(i+1));
                    }
                } catch (Exception e) {
                    System.err.println("Error: "+e.getMessage());
                    Thread.currentThread().getThreadGroup().interrupt();
                    System.exit(0);

                }
            }
        }
    }
    static class MyThread extends Thread{
        private MyService service;

        public MyThread(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.testMethod();
        }
    }

    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread a = new MyThread(myService);
        MyThread b = new MyThread(myService);
        a.start();
        b.start();
    }
}
