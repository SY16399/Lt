package org.example.Timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerCancelError {
    static class MyTaskA extends TimerTask {
        private int i;
        public MyTaskA(int i) {
            super();
            this.i = i;
        }
        @Override
        public void run() {
            System.out.println(" 第" + i + "次没有被cancel取消");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        long nowTime = System.currentTimeMillis();
        System.out.println("当前时间为："+nowTime);
        System.out.println("计划时间为："+nowTime);
        while(true){
            i++;
            Timer timer = new Timer();
            MyTaskA taskA = new MyTaskA(i);
            timer.schedule(taskA,new Date(nowTime));
            timer.cancel();
        }
    }
}
