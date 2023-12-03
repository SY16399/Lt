package org.example.InheritableThreadLocal;

import java.util.Date;

public class InheritableThreadLocal {
    static class InheritableThreadLocalExt extends java.lang.InheritableThreadLocal{
        @Override
        protected Object childValue(Object parentValue) {
            return parentValue+" 我在子线程里面加的哦~！";
        }

        @Override
        protected Object initialValue() {
            return new Date().getTime();
        }
    }
    static class Tools {
        public static InheritableThreadLocalExt t1 = new InheritableThreadLocalExt();
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("在ThreadA线程中取值=" + Tools.t1.get());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        try{
            for (int i = 0; i < 10; i++) {
                if (Tools.t1.get() == null) {
                    Tools.t1.set("此值时main线程放入的！");//在此处执行。
                }
                System.out.println("    在Main线程中取值=" + Tools.t1.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA a = new ThreadA();
            a.start();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
