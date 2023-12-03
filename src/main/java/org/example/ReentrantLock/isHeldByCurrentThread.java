package org.example.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class isHeldByCurrentThread {
    static class Service{
        private final ReentrantLock lock = new ReentrantLock();
        private final ReentrantLock lock2 = new ReentrantLock();
        public void serviceMethod(){
            try{
                System.out.println(lock.isHeldByCurrentThread());
                System.out.println(lock2.isHeldByCurrentThread());
                lock.lock();
                System.out.println(lock.isHeldByCurrentThread());
                System.out.println(lock2.isHeldByCurrentThread());
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
