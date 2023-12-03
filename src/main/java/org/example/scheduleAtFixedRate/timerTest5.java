package org.example.scheduleAtFixedRate;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class timerTest5 {
    static class MyTask extends TimerTask {

        @Override
        public void run() {
            try {
                System.out.println("begin timer="+System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("    end timer="+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        long nowTime = System.currentTimeMillis();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task,new Date(nowTime),2000);
    }
}
