package org.example.scheduleAtFixedRate;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test9 {
    static class MyTask extends TimerTask{

        @Override
        public void run() {
            System.out.println("begin timer="+System.currentTimeMillis());
            System.out.println("    end timer="+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        long nowTime = System.currentTimeMillis();
        System.out.println("现在执行时间："+nowTime);
        long runTime = nowTime - 20000;
        System.out.println("计划执行时间："+runTime);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,new Date(runTime),2000);
    }
}
