package org.example.ReentrantLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test4 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println("begin "+System.currentTimeMillis());
        long begin = System.currentTimeMillis();
        unsafe.park(false, 0L);
        System.out.println("    end " + System.currentTimeMillis());
        long end = System.currentTimeMillis();
        System.out.println("all = " + (end - begin));
    }
}
