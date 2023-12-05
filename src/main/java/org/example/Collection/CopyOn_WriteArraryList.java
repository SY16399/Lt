package org.example.Collection;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOn_WriteArraryList {
    static class MyService {
        public static CopyOnWriteArrayList list = new CopyOnWriteArrayList();
    }

    static class MyThread extends Thread {
        private MyService service;

        public MyThread(MyService service) {
            this.service = service;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                service.list.add("anyString");
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
        System.out.println(service.list.size());
        System.out.println("可以随机取得的值：" + service.list.get(5));
    }
}
