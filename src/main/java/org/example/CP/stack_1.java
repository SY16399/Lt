package org.example.CP;

import java.util.ArrayList;
import java.util.List;

public class stack_1 {
    static class MyStack {
        private final List<String> list = new ArrayList<>();

        synchronized public void push() {
            try {
                //更改条件判断语句
                while (list.size() == 1) {
                    this.wait();
                }
                list.add("anyString=" + Math.random());
                this.notify();
                System.out.println("push=" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public String pop() {
            String returnValue = "";
            try {
                //更改条件判断语句
                while (list.size() == 0) {
                    System.out.println("pop操作中的："
                            + Thread.currentThread().getName() + " 线程呈wait状态");
                    this.wait();
                }
                returnValue = "" + list.get(0);
                list.remove(0);
                this.notify();
                System.out.println("pop=" + list.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return returnValue;
        }
    }

    //生产者
    static class P {
        private MyStack myStack;

        public P(MyStack myStack) {
            super();
            this.myStack = myStack;
        }

        public void pushService() {
            myStack.push();
        }
    }

    //消费者
    static class C {
        private MyStack myStack;

        public C(MyStack myStack) {
            super();
            this.myStack = myStack;
        }

        public void popService() {
            System.out.println("pop=" + myStack.pop());
        }
    }

    static class p_Thread extends Thread {
        private P p;

        public p_Thread(P p) {
            super();
            this.p = p;
        }

        @Override
        public void run() {
            while (true){
                p.pushService();
            }
        }
    }
    static class c_Thread extends Thread{
        private C c;

        public c_Thread(C c) {
            super();
            this.c = c;
        }

        @Override
        public void run() {
            while(true){
                c.popService();
            }
        }
    }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        P p = new P(myStack);
        C c = new C(myStack);
        C c1 = new C(myStack);
        C c2 = new C(myStack);
        C c3 = new C(myStack);
        p_Thread pThread = new p_Thread(p);
        c_Thread cThread = new c_Thread(c);
        c_Thread cThread1 = new c_Thread(c1);
        c_Thread cThread2 = new c_Thread(c2);
        c_Thread cThread3 = new c_Thread(c3);
        pThread.start();
        cThread.start();
        cThread1.start();
        cThread2.start();
        cThread3.start();
    }
}
