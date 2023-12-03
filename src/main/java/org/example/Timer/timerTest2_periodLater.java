package org.example.Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class timerTest2_periodLater {
    static class MyTaskA extends TimerTask{

        @Override
        public void run() {
            try{
                System.out.println("A begin timer="+System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("A           end timer="+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        long nowTime = System.currentTimeMillis();
        System.out.println("当前时间为：  "+nowTime);
        System.out.println("    计划时间为：  "+nowTime);
        MyTaskA taskA = new MyTaskA();
        Timer timer = new Timer();
        timer.schedule(taskA,new Date(nowTime),3000);
    }
}
