package org.example.ReentrantLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Signal_test {
    static class MyThread extends  Thread{
        private Unsafe unsafe;
        private Thread mainThread;

        public MyThread(Unsafe unsafe, Thread mainThread) {
            this.unsafe = unsafe;
            this.mainThread = mainThread;
        }

        @Override
        public void run() {
            try{
                Thread.sleep(6000);
                unsafe.unpark(mainThread);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InterruptedException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);

        MyThread subThread = new MyThread(unsafe,Thread.currentThread());
        subThread.start();
        Thread.sleep(200);
        System.out.println("begin "+System.currentTimeMillis());
        long begin = System.currentTimeMillis();
        unsafe.park(false, 0L);
        System.out.println("    end " + System.currentTimeMillis());
        long end = System.currentTimeMillis();
        System.out.println("all = " + (end - begin));
    }

}
