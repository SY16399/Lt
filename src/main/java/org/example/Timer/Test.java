package org.example.Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Test {
    static class MyTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("任务执行了，时间为："+System.currentTimeMillis());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        long nowTime = System.currentTimeMillis();
        System.out.println(" 当前时间为：" + nowTime);
        long scheduleTime1 = (nowTime + 5000);
        long scheduleTime2 = (nowTime + 8000);
        System.out.println(" 计划时间1为："+scheduleTime1);
        System.out.println(" 计划时间2为："+scheduleTime2);
        MyTask task1 = new MyTask();
        MyTask task2 = new MyTask();
        Timer timer = new Timer();
        timer.schedule(task1,new Date(scheduleTime1));
        timer.schedule(task2,new Date(scheduleTime2));
    }
}
