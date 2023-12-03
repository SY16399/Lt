package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class condition123 {
    static class MyService{
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        volatile private int nextWhoPrint = 1;
        public void testMethod1(){
            try {
                lock.lock();
                while (nextWhoPrint!=1){
                    condition.await();
                }
                System.out.println("AAA");
                nextWhoPrint = 2;
                condition.signalAll();
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void testMethod2(){
            try {
                lock.lock();
                while (nextWhoPrint!=2){
                    condition.await();
                }
                System.out.println("    BBB");
                nextWhoPrint = 3;
                condition.signalAll();
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void testMethod3(){
            try {
                lock.lock();
                while (nextWhoPrint!=3){
                    condition.await();
                }
                System.out.println("        CCC");
                nextWhoPrint = 1;
                condition.signalAll();
                lock.unlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyService service = new MyService();
        Runnable run1 = service::testMethod1;
        Runnable run2 = service::testMethod2;
        Runnable run3 = service::testMethod3;
        for (int i = 0; i <5; i++) {
            Thread a = new Thread(run1);
            a.start();
            Thread b = new Thread(run2);
            b.start();
            Thread c = new Thread(run3);
            c.start();
        }
    }

}
