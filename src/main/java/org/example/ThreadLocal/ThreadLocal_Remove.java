package org.example.ThreadLocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocal_Remove {
    static class MyThreadLocal extends ThreadLocal{
        private static AtomicInteger count = new AtomicInteger(0);
        @Override
        protected void finalize() throws Throwable {
            System.out.println("MyThreadLocal finalize()"+count.addAndGet(1));
        }
    }
    static class Userinfo{
        private static AtomicInteger count = new AtomicInteger(0);

        @Override
        protected void finalize() throws Throwable {
            System.out.println("------------Userinfo protect void finalize"+count.addAndGet(1));
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 9000; i++) {
            MyThreadLocal threadLocal = new MyThreadLocal();
            Userinfo userinfo = new Userinfo();
            threadLocal.set(userinfo);
            threadLocal.remove();
        }
        MyThreadLocal threadLocal = new MyThreadLocal();
        System.out.println("9000 end!");
        List list = new ArrayList();
        for (int i = 0; i < 900000000; i++) {
            String newString = new String(""+(i+1));
            Thread.yield();
            Thread.yield();
            Thread.yield();
            Thread.yield();
        }
        //打印threadLocal实现强引用
        //至少在内存中保留一个threadLocal对象
        list.add(threadLocal);
        System.out.println("zzzzzzzzzzzzz"+threadLocal);
    }
}
