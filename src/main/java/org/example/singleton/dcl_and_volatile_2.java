package org.example.singleton;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class dcl_and_volatile_2 {
    static int count = 100;
    static class OneInstanceService {
        public int i_am_has_state = 0;
        private volatile static OneInstanceService test;

        public OneInstanceService() {
            this.i_am_has_state = new Random().nextInt(200) + 1;
        }

        public static OneInstanceService getTest1() {
            if (test == null) {
                synchronized (OneInstanceService.class) {
                    if (test == null) {
                        test = new OneInstanceService();
                    }
                }
            }
            return test;
        }

        public static void reset() {
            test = null;
            count = 100;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock1 = new ReentrantLock();
        Condition condition = lock1.newCondition();
        Lock lock2 = new ReentrantLock();
        Condition condition1 = lock2.newCondition();
        for (; ; ) {
            //允许一个或多个线程等待直到在其他线程中执行的一组操作完成的同步辅助。
            CountDownLatch latch = new CountDownLatch(1);
            CountDownLatch end = new CountDownLatch(100);
            for (int i = 0; i < 100; i++) {
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            //导致当前线程等到锁存器计数到零，除非线程是 interrupted 。
                            //创建 100 个线程在这里等待
                            lock1.lock();
                            condition.await();
                            lock1.unlock();
                            OneInstanceService one = OneInstanceService.getTest1();
                            if (one.i_am_has_state == 0) {
                                System.out.println("one.i_am_has_state == 0 进程结束");
                                System.exit(0);
                            }
                            //减少锁存器的计数，如果计数达到零，释放所有等待的线程。
                            lock2.lock();
                            count--;
                            condition1.signalAll();
                            lock2.unlock();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t1.start();
            }
            //循环完毕，创建结束，减掉计数1，线程被唤醒开始执行
            lock1.lock();
            condition.signalAll();
            lock1.unlock();
            //等待计数为0,也就是100个线程执行完成
            while (count !=0){
                if (count==0){
                    break;
                }
            }
            //重置
            OneInstanceService.reset();
        }
    }
}
