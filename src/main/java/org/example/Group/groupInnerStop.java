package org.example.Group;

public class groupInnerStop {
    static class MyThread extends Thread {
        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            System.out.println("ThreadName=" + Thread.currentThread().getName()
                    + "准备开始死循环");
            while (!this.isInterrupted()) {
            }
            System.out.println("ThreadName=" + Thread.currentThread().getName()
                    + "结束:)");
        }
    }

    public static void main(String[] args) {
        try{
            ThreadGroup group = new ThreadGroup("我的线程组");
            for (int i = 0; i < 5; i++) {
                MyThread thread = new MyThread(group,"线程 "+(i+1));
                thread.start();
            }
            Thread.sleep(5000);
            group.interrupt();
            System.out.println("调用了 interrupt() 方法");
        } catch (InterruptedException e) {
            System.out.println("停了停了！");
            e.printStackTrace();
        }
    }

}
