package org.example.Collection;

import java.util.concurrent.CopyOnWriteArraySet;

public final class CopyOn_WriteArrarySet {
    static class MyService {
        public static CopyOnWriteArraySet set = new CopyOnWriteArraySet();
    }

    static class MyThread extends Thread {
        private MyService service;

        public MyThread(MyService service) {
            this.service = service;
            
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                service.set.add(Thread.currentThread().getName()+"anything"+(i+1));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        MyThread[] aArray = new MyThread[100];
        for (int i = 0; i < aArray.length; i++) {
            aArray[i] = new MyThread(service);
        }
        for (int i = 0; i < aArray.length; i++) {
            aArray[i].start();
        }
        Thread.sleep(3000);
        System.out.println(service.set.size());
    }

}
