package org.example.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class isLocked {
    static class Service{
        private ReentrantLock lock = new ReentrantLock();
        public void serviceMethod(){
            try{
                System.out.println(lock.isLocked());
                lock.lock();
                System.out.println(lock.isLocked());
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final Service service1 = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service1.serviceMethod();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
