package org.example.CP;

public class p_r_test {
    //创建储存值的对象ValueObject
    static class ValueObject {
        public static String value = "";
    }

    //消费者
    static class C {
        private String lock;

        public C(String lock) {
            super();
            this.lock = lock;
        }

        public void getValue() {
            try {
                synchronized (lock) {
                    while (ValueObject.value.equals("")) {
                        System.out.println("消费者 " + Thread.currentThread().getName() + " WAITING了☆");
                        lock.wait();
                    }
                    System.out.println("消费者 " + Thread.currentThread().getName()
                            + " RUNNABLE了");
                    ValueObject.value = "";
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //生产者
    static class P {
        private String lock;

        public P(String lock) {
            super();
            this.lock = lock;
        }

        public void setValue() {
            try {
                synchronized (lock) {
                    while (!ValueObject.value.equals("")) {
                        System.out.println("生产者 "
                                + Thread.currentThread().getName() + " WAITING了★");
                        lock.wait();
                    }
                    System.out.println("生产者 " + Thread.currentThread().getName()
                            + " RUNNABLE了");
                    String value = System.currentTimeMillis() + "_" + System.nanoTime();
                    System.out.println(" set的值是：" + value);
                    ValueObject.value = value;
                    lock.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //创建两个线程，一个消费者线程，一个生产者线程。
    static class ThreadP extends Thread {
        private P p;

        public ThreadP(P p) {
            super();
            this.p = p;
        }

        @Override
        public void run() {
            while (true) {
                p.setValue();
            }
        }
    }

    static class ThreadC extends Thread {
        private C c;

        public ThreadC(C c) {
            super();
            this.c = c;
        }

        @Override
        public void run() {
            while (true) {
                c.getValue();
            }
        }
    }

    /*public static void main(String[] args) {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadC threadC = new ThreadC(c);
        ThreadP threadP = new ThreadP(p);
        threadP.start();
        threadC.start();
    }*/
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);
        ThreadC[] threadC = new ThreadC[2];
        ThreadP[] threadP = new ThreadP[2];
        for (int i = 0; i < 2; i++) {
            threadP[i] = new ThreadP(p);
            threadP[i].setName(" 生产者" + (i + 1));
            threadC[i] = new ThreadC(c);
            threadC[i].setName(" 消费者" + (i + 1));
            threadP[i].start();
            threadC[i].start();
        }
        Thread.sleep(5000);
        Thread[] threads = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
        Thread.currentThread().getThreadGroup().enumerate(threads);
        for (int i = 0; i < threads.length; i++) {
            System.out.println(threads[i].getName() + " " +
                    threads[i].getState());
        }
    }
}
