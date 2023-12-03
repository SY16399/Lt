package org.example.InheritableThreadLocal;

public class ThreadLocalNoExtends {
    static class Tools{
        public static ThreadLocal t1 = new ThreadLocal();
    }
    static class ThreadA extends Thread{
        @Override
        public void run() {
            try{
                for (int i = 0; i < 10; i++) {
                    System.out.println("在ThreadA线程中取值="+Tools.t1.get());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                if (Tools.t1.get() == null){
                    Tools.t1.set("此值是main线程放入的！");
                }
                System.out.println("    在Main线程中取值="+Tools.t1.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA a = new ThreadA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
