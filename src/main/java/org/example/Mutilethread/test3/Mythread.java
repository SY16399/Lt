package org.example.Mutilethread.test3;


public class Mythread extends Thread{
    @Override
    public void run() {
        super.run();
        try {
        for (int i = 0; i < 500000; i++) {
            if (this.isInterrupted()){
                System.out.println("已经是停止状态了，退出！");
                throw new InterruptedException();
            }
            System.out.println("i="+(i+1));
        }
        System.out.println("for之下的代码");
        }catch (InterruptedException e) {
            System.out.println("进入run中的catch了");
            //throw new RuntimeException(e);
        }


    }

    public static void main(String[] args) throws InterruptedException {
        Mythread thread = new Mythread();
        thread.start();
        //Thread.sleep(20000);
        thread.interrupt();

        System.out.println("end......");
    }
}
