package org.example.Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskCancelMethod {
    static class MyTaskA extends TimerTask{

        @Override
        public void run() {
            System.out.println("A run timer="+System.currentTimeMillis());
            this.cancel();
            System.out.println("A 任务自己移除自己");
        }
    }
    static class MyTaskB extends TimerTask{

        @Override
        public void run() {
            System.out.println("B run timer="+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        long nowTime = System.currentTimeMillis();
        System.out.println("当前时间为："+nowTime);
        System.out.println("计划时间为："+nowTime);
        MyTaskA task1 = new MyTaskA();
        MyTaskB task2 = new MyTaskB();
        Timer timer = new Timer();
        timer.schedule(task1,new Date(nowTime),4000L);
        timer.schedule(task2,new Date(nowTime),4000L);
     }
}
