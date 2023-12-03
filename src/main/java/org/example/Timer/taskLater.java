package org.example.Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class taskLater {
    static class MyTaskA extends TimerTask {
        @Override
        public void run() {
            try {
                System.out.println("A begin timer=" + System.currentTimeMillis());
                Thread.sleep(20000);
                System.out.println("A   end Timer=" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class MyTaskB extends TimerTask{

        @Override
        public void run() {
            System.out.println("B begin timer="+System.currentTimeMillis());
            System.out.println("B   end timer="+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        long nowTime = System.currentTimeMillis();
        System.out.println("当前时间为："+nowTime);
        long scheduleTime1 = nowTime;
        long scheduleTime2 = nowTime+5000;
        System.out.println("计划时间1为："+scheduleTime1);
        System.out.println("计划时间2为："+scheduleTime2);
        MyTaskA taskA = new MyTaskA();
        MyTaskB taskB = new MyTaskB();
        Timer timer = new Timer();
        timer.schedule(taskA,new Date(scheduleTime1));
        timer.schedule(taskB,new Date(scheduleTime2));
    }
}
