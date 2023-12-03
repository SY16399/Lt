package org.example.singleton;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class dcl_and_volatile {
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
        }
    }

    public static void main(String[] args) throws InterruptedException {
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
                            latch.await();
                            OneInstanceService one = OneInstanceService.getTest1();
                            if (one.i_am_has_state == 0) {
                                System.out.println("one.i_am_has_state == 0 进程结束");
                                System.exit(0);
                            }
                            //减少锁存器的计数，如果计数达到零，释放所有等待的线程。
                            end.countDown();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t1.start();
            }
            //循环完毕，创建结束，减掉计数1，线程被唤醒开始执行
            latch.countDown();
            //等待计数为0,也就是100个线程执行完成
            end.await();
            //重置
            OneInstanceService.reset();
        }
    }
}
