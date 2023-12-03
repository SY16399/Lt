package org.example.Timer;

import java.util.Timer;
import java.util.TimerTask;

public class timerTest3 {
    static class MyTask extends TimerTask{

        @Override
        public void run() {
            System.out.println("运行了！时间为"+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Timer timer = new Timer();
        System.out.println("当前时间为："+System.currentTimeMillis());
        timer.schedule(task,3000L,5000);
    }
}
