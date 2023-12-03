package org.example.ReentrantLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test3 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //使用反射成员类
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println("begin " + System.currentTimeMillis());
        long begin = System.currentTimeMillis();
        //3秒的纳秒值是3000000000
        //3秒的微秒值是3000000
        //3秒的毫秒值是3000
        //3秒
        //如果传入 false，第二个参数时间单位为纳秒
        unsafe.park(false, 3000000000L);
        System.out.println("    end " + System.currentTimeMillis());
        long end = System.currentTimeMillis();
        System.out.println("all = " + (end - begin));

    }
}
