package org.example.Join;

public class join_sleep {
    static class ThreadA extends Thread{
        private ThreadB threadB;

        public ThreadA(ThreadB threadB) {
            this.threadB = threadB;
        }

        @Override
        public void run() {
            try{
                synchronized (threadB){
                    threadB.start();
                    threadB.join();//执行join（）方法的一瞬间，b立即释放锁。
                    for (int i = 0; i < Integer.MAX_VALUE; i++) {
                        String newString = new String();
                        Math.random();
                    }
                    //不释放锁
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadB extends Thread{
        @Override
        public void run() {
            try{
                System.out.println("    b run begin timer="+System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("    b run   end timer="+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized public void bService(){
            System.out.println("打印了bService timer="+System.currentTimeMillis());
        }
    }
    static class ThreadC extends Thread{
        private ThreadB threadB;

        public ThreadC(ThreadB threadB) {
            this.threadB = threadB;
        }

        @Override
        public void run() {
            threadB.bService();
        }
    }

    public static void main(String[] args) {
        try{
            ThreadB b = new ThreadB();
            ThreadA a = new ThreadA(b);
            a.start();
            Thread.sleep(1000);
            ThreadC c = new ThreadC(b);
            c.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
