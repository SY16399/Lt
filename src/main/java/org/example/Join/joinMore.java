package org.example.Join;

public class joinMore {
    static class ThreadA extends Thread {
        private ThreadB threadB;

        public ThreadA(ThreadB threadB) {
            this.threadB = threadB;
        }

        @Override
        public void run() {
            try {
                synchronized (threadB) {
                    System.out.println("begin A ThreadName="
                            + Thread.currentThread().getName() + " "
                            + System.currentTimeMillis());
                    Thread.sleep(500);
                    System.out.println("    end A threadName="
                            + Thread.currentThread().getName() + " "
                            + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        synchronized public void run() {
            try {
                System.out.println("begin B ThreadName="
                        + Thread.currentThread().getName() + " "
                        + System.currentTimeMillis());
                Thread.sleep(500);
                System.out.println("    end B   ThreadName="
                        + Thread.currentThread().getName() + " "
                        + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

        public static void main(String[] args) {
        try {
            ThreadB b = new ThreadB();
            ThreadA a = new ThreadA(b);
            a.start();
            b.start();
            b.join(200);
            System.out.println("        main end " + System.currentTimeMillis());
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/*    public static void main(String[] args) {
        ThreadB b = new ThreadB();
        ThreadA a = new ThreadA(b);
        a.start();
        b.start();
        System.out.println("    main end=" + System.currentTimeMillis());
    }*/
}



