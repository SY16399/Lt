package org.example.Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class NewAlgorithm {
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
    static class MyTaskC extends TimerTask{

        @Override
        public void run() {
            System.out.println("C run timer="+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        long nowTime = System.currentTimeMillis();
        System.out.println("当前时间为："+nowTime);
        System.out.println("计划时间为："+nowTime);
        MyTaskA taskA = new MyTaskA();
        MyTaskB taskB = new MyTaskB();
        MyTaskC taskC = new MyTaskC();
        Timer timer = new Timer();
        timer.schedule(taskA,new Date(nowTime),2000L);
        timer.schedule(taskB,new Date(nowTime),2000L);
        timer.schedule(taskC,new Date(nowTime),2000L);
    }
}
