package org.example.wait_notify;

import java.util.ArrayList;
import java.util.List;

public class waitOld {
    static class ValueObject {
        public static List<String> list = new ArrayList<>();
    }

    static class Add {
        private String lock;

        public Add(String lock) {
            super();
            this.lock = lock;
        }

        public void add() {
            synchronized (lock) {
                ValueObject.list.add("anyString");
                lock.notifyAll();
            }
        }
    }

    static class Subtract {
        private String lock;

        public Subtract(String lock) {
            super();
            this.lock = lock;
        }

        public void subtract() {
            try {
                synchronized (lock) {
                    while (ValueObject.list.size() == 0) {
                        System.out.println("wait begin ThreadName="
                                + Thread.currentThread().getName());
                        lock.wait();
                        System.out.println("wait    end ThreadName="
                                + Thread.currentThread().getName());
                    }
                    ValueObject.list.remove(0);
                    System.out.println("list size="+ValueObject.list.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadAdd extends Thread{
        private Add p;

        public ThreadAdd(Add p) {
            super();
            this.p = p;
        }

        @Override
        public void run() {
            p.add();
        }
    }
    static class ThreadSubract extends Thread{
        private Subtract r;

        public ThreadSubract(Subtract r) {
            super();
            this.r = r;
        }

        @Override
        public void run() {
            r.subtract();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        Add add = new Add(lock);
        Subtract subtract = new Subtract(lock);
        ThreadSubract threadSubract1 = new ThreadSubract(subtract);
        threadSubract1.setName("threadSubract1");
        threadSubract1.start();
        ThreadSubract threadSubract2 = new ThreadSubract(subtract);
        threadSubract2.setName("threadSubract2");
        threadSubract2.start();
        Thread.sleep(1000);
        ThreadAdd threadAdd = new ThreadAdd(add);
        threadAdd.setName("threadAdd");
        threadAdd.start();
    }
}
