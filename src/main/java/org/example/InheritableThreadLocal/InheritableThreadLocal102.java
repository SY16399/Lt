package org.example.InheritableThreadLocal;

import java.lang.InheritableThreadLocal;

public class InheritableThreadLocal102 {
    static class Tools {
        static InheritableThreadLocal t1 = new InheritableThreadLocal();
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("在ThreadA线程中取值=" + Tools.t1.get());
                    Thread.sleep(1000);
                    if (i == 5) {
                        Tools.t1.set("我是ThreadA的 newnewnew 最新的值");
                        System.out.println("ThreadA 已经存在最新的值--------------------");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (Tools.t1.get() == null) {
            Tools.t1.set("此值是main线程放入的");
        }
        System.out.println("    在Main线程中取值=" + Tools.t1.get());
        Thread.sleep(100);
        ThreadA a = new ThreadA();
        a.start();
        Thread.sleep(3000);
        for (int i = 0; i < 10; i++) {
            System.out.println("main end get value=" + Tools.t1.get());
            Thread.sleep(1000);
        }
        ;
    }
}
