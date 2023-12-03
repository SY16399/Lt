package org.example.Group;

public class RunE {
    public static void main(String[] args) {
        //这里是通过实例引用访问 static 成员 'java.lang.Thread.activeCount()'
        //正常 Thread.activeCount() 就可以了，为了更直观的感受
        Thread[] threads = new Thread[Thread.currentThread().activeCount()];
        Thread.enumerate(threads);
        for (int i = 0; i < threads.length; i++) {
            System.out.println(threads[i].getName());
        }
    }
}
