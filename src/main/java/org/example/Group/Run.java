package org.example.Group;

public class Run {
    public static void main(String[] args) {
        //方法 activeGroupCount() 取得当前线程组对象中的子线程组数量
        //方法 enumerate() 的作用是将线程组中的子线程组以复制的形式
        // 复制到 ThreadGroup[] 数组对象中
        System.out.println("A处线程："+Thread.currentThread().getName()
                +" 所属的线程组名为："
                +Thread.currentThread().getThreadGroup().getName()
                +" "+" 中有线程组数量："
                +Thread.currentThread().getThreadGroup().activeGroupCount());
        ThreadGroup group = new ThreadGroup("新的组"); // 自动加入到 main 组中
        System.out.println("B处线程："+Thread.currentThread().getName()
                +" 所属的线程组名为："
                +Thread.currentThread().getThreadGroup().getName()
                +" "+" 中有线程组数量："
                +Thread.currentThread().getThreadGroup().activeGroupCount());
        ThreadGroup[] threadGroups = new ThreadGroup[Thread.currentThread().getThreadGroup()
                .activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadGroups);
        for (int i = 0; i < threadGroups.length; i++) {
            System.out.println("第一个线程组名称为:"+threadGroups[i].getName());
        }
    }
}
