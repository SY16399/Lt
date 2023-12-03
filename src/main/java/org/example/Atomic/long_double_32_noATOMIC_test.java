package org.example.Atomic;

public class long_double_32_noATOMIC_test {
    static class MyService {
        public long i;
    }

    static class ThreadA extends Thread {
        private MyService service;

        public ThreadA(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            while (true) {
                service.i = 1;
            }
        }
    }

    static class ThreadB extends Thread {
        private MyService service;

        public ThreadB(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            while (true) {
                service.i = -1;
            }
        }
    }

    public static void main(String[] args) {
        try {
            MyService service = new MyService();
            ThreadA a = new ThreadA(service);
            ThreadB b = new ThreadB(service);
            a.start();
            b.start();
            Thread.sleep(1000);
            System.out.println("long 1 二进制值是：" + Long.toBinaryString(1));
            System.out.println("long -1 二进制值是：" + Long.toBinaryString(-1));
            while (true) {
                long getValue = service.i;
                if (getValue != 1 && getValue != -1) {
                    System.out.println("            i的值是：" + Long.toBinaryString(getValue) + " 十进制是：" + getValue);
                    System.exit(0);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
