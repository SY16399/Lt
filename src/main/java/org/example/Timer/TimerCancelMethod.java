package org.example.Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerCancelMethod {
    static class MyTaskA extends TimerTask {

        @Override
        public void run() {
            System.out.println("A run timer="+System.currentTimeMillis());
        }
    }
    static class MyTaskB extends TimerTask{

        @Override
        public void run() {
            System.out.println("B run timer="+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long nowTime = System.currentTimeMillis();
        System.out.println("当前时间为："+nowTime);
        System.out.println("计划时间为："+nowTime);
        MyTaskA task1 = new MyTaskA();
        MyTaskB task2 = new MyTaskB();
        Timer timer = new Timer();
        timer.schedule(task1,new Date(nowTime),2000L);
        timer.schedule(task2,new Date(nowTime),2000L);
        Thread.sleep(10000);
        timer.cancel();         //全部任务都取消
    }
}
