package org.example.ThreadLocal;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        ThreadLocal local = new ThreadLocal();
        ThreadLocal local1 = new ThreadLocal();
        local.set("我是任意的值");
        System.out.println(local.get());
        System.out.println(local1.get());
        System.out.println(local.get());
    }
}
