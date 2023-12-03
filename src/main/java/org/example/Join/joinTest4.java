package org.example.Join;

public class joinTest4 {
    static class ThreadA extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                String newString = new String();
                Math.random();
            }
        }
    }
    static class ThreadB extends  Thread{
        @Override
        public void run() {
            try{
                ThreadA threadA = new ThreadA();
                threadA.start();
                threadA.join();
                System.out.println("线程B在run end 处打印了");
            } catch (InterruptedException e) {
                System.out.println("线程B在catch处打印了");
                e.printStackTrace();
            }
        }
    }
    static class ThreadC extends  Thread{
        private ThreadB threadB;

        public ThreadC(ThreadB threadB) {
            this.threadB = threadB;
        }

        @Override
        public void run() {
            threadB.interrupt();
        }
    }

    public static void main(String[] args) {
        try{
            ThreadB b = new ThreadB();
            b.start();
            Thread.sleep(500);
            ThreadC c = new ThreadC(b);
            c.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
