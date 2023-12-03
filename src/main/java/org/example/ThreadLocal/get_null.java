package org.example.ThreadLocal;

public class get_null {
    public static ThreadLocal threadLocal = new ThreadLocal();
    public static void main(String[] args) {
        if (threadLocal.get() == null){
            System.out.println("从未放过值");
            threadLocal.set("我的值");
        }
        System.out.println(threadLocal.get());
        System.out.println(threadLocal.get());
    }
}
