package org.example.Collection;


import java.util.concurrent.ConcurrentLinkedQueue;

public class Concurrent_LinkedQueue {
    static class MyService {
        public ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
    }

    static class MyThread extends Thread {
        private MyService myService;

        public MyThread(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                myService.queue.add(Thread.currentThread().getName() + (i + 1));
            }
        }
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        service.queue.add("a");
        service.queue.add("b");
        service.queue.add("c");
        System.out.println("begin size:" + service.queue.size());
        System.out.println(service.queue.peek());
        System.out.println("    end size:" + service.queue.size());

    }
}
