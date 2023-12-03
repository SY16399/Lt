package org.example.Group;

public class groupAddThreadMethodLevel {
    public static void main(String[] args) {
        //在 main 组中添加线程组 A，然后在线程组 A 添加线程对象 z。
        //方法 activeGroupCount() 和 activeCount()的值不是固定的。
        //而是系统的一个快照
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
                                //取得 main 主线程所在的线程组
        ThreadGroup group = new ThreadGroup(mainGroup,"A");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("runMethod!");
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread newThread = new Thread(group,runnable);
        newThread.setName("z");
        newThread.start();
        //线程必须是程序启动后才归属到线程组 A 中，因为在调用 start() 方法时会调用
        //group.add(this);
        ThreadGroup[] listGroup = new ThreadGroup[Thread.currentThread()
                .getThreadGroup().activeGroupCount()];
        //Thread.currentThread().getThreadGroup().enumerate(listGroup);
        mainGroup.enumerate(listGroup);
        System.out.println("main 线程中有多少个子线程组： "+listGroup.length+" 名字为："+listGroup[0].getName());
        Thread[] listThread = new Thread[listGroup[0].activeCount()];
        listGroup[0].enumerate(listThread);
        System.out.println(listThread[0].getName());
    }
}
