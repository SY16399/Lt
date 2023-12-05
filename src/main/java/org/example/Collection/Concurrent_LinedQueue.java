package org.example.Collection;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Concurrent_LinedQueue {
    static class MyService {
        public ConcurrentLinkedDeque deque = new ConcurrentLinkedDeque();

        public MyService() {
            for (int i = 0; i < 4; i++) {
                deque.add("string" + (i + 1));
            }
        }
    }

    static class MyThreadF extends Thread {
        private MyService service;

        public MyThreadF(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            System.out.println("value=" + service.deque.pollFirst()
                    + " queue.size()=" + service.deque.size());
        }
    }static class MyThreadL extends Thread {
        private MyService service;

        public MyThreadL(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            System.out.println("value=" + service.deque.pollLast()
                    + " queue.size()=" + service.deque.size());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        MyThreadF aF = new MyThreadF(service);
        MyThreadF bF = new MyThreadF(service);
        MyThreadL aL = new MyThreadL(service);
        MyThreadL bL = new MyThreadL(service);
        aF.start();
        Thread.sleep(1000);
        aL.start();
        Thread.sleep(1000);
        bF.start();
        Thread.sleep(1000);
        bL.start();
    }
}
