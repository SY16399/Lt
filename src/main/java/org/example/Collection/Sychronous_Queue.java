package org.example.Collection;

import java.util.concurrent.SynchronousQueue;

public class Sychronous_Queue {
    static class MyService {
        public static SynchronousQueue queue = new SynchronousQueue();

        public void putMethod() {
            try {
                String putString = "anyString" + Math.random();
                queue.put(putString);
                System.out.println(" put=" + putString);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void takeMethod() {
            try {
                System.out.println("    take=" + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        Thread threadPut = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    service.putMethod();
                }
            }
        };
        Thread threadTake = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    service.takeMethod();
                }
            }
        };
        threadTake.start();
        Thread.sleep(2000);
        threadPut.start();
    }
}
