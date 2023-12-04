package org.example.Collection;


import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class Concurrent_Hashtable {
    static class MyService {
        public ConcurrentHashMap map = new ConcurrentHashMap();

        public MyService() {
            for (int i = 0; i < 100000; i++) {
                map.put(Thread.currentThread().getName() + i + 1, "abc");
            }
        }

        public void testMethod() {
            Iterator iterator = map.keySet().iterator();
            while (iterator.hasNext()){
                try {
                    Object object = iterator.next();
                    iterator.remove();
                    System.out.println(map.size()+" "+Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
        }
    }

    static class MyThread extends Thread {
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
