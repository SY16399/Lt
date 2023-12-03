package org.example.ThreadLocal;

public class get_null_ok {
    static class ThreadLocalExt extends ThreadLocal{
        @Override
        protected Object initialValue() {
            return "我是默认值不再为null";
        }
    }
    private static ThreadLocalExt t1 = new ThreadLocalExt();
    public static void main(String[] args) {
        if (t1.get() == null){
            System.out.println("从未放过值");
            t1.set("我的值");
        }
        System.out.println(t1.get());
        System.out.println(t1.get());
    }

}
