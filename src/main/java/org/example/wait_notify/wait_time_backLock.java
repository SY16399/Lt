package org.example.wait_notify;

public class wait_time_backLock {
    static class MyServicec {
        public void testMethod() {
            try {
                synchronized (this) {
                    long start = System.currentTimeMillis();
                    System.out.println("wait begin " + Thread.currentThread().
                            getName() + " " + start);
                    wait(5000);
                    long end = System.currentTimeMillis();
                    System.out.println("wait    end " + Thread.currentThread()
                            .getName() + " " + end + " 总用时=" + (end - start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public void longTimeSyn() {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThreadA extends Thread {
        private MyServicec servicec = new MyServicec();

        public MyThreadA(MyServicec servicec) {
            super();
            this.servicec = servicec;
        }

        @Override
        public void run() {
            servicec.testMethod();
        }
    }

    static class MyThreadB extends Thread {
        private MyServicec servicec;

        public MyThreadB(MyServicec servicec) {
            super();
            this.servicec = servicec;
        }

        @Override
        public void run() {
            servicec.longTimeSyn();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyServicec servicec = new MyServicec();
        MyThreadA[] arrays = new MyThreadA[10];
        for (int i = 0; i < 10; i++) {
            arrays[i] = new MyThreadA(servicec);
        }
        for (int i = 0; i < 10; i++) {
            arrays[i].start();
        }
        Thread.sleep(10);// 防止b线程现开启
        MyThreadB b = new MyThreadB(servicec);
        b.start();
    }
}
