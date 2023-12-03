package org.example.ReentrantLock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class Test2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe)f.get(null);//null 表示获取的是某个静态变量而不是某个实例
        System.out.println("begin "+System.currentTimeMillis());
        System.currentTimeMillis();
        unsafe.park(true,System.currentTimeMillis()+3000);
        System.out.println(" end "+System.currentTimeMillis());
    }
}
