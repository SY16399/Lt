package org.example.Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test2 {
    static class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务执行了，时间为："+System.currentTimeMillis());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        long nowTime = System.currentTimeMillis();
        System.out.println(" 当前时间为：" + nowTime);
        long scheduleTime = (nowTime - 5000);
        System.out.println(" 计划时间为："+scheduleTime);
        MyTask task = new MyTask();
        Timer timer = new Timer();
        timer.schedule(task,new Date(scheduleTime),4000L);
    }
}
