package org.example.WUYONG;

public class MyThread extends Thread {
    private volatile boolean isRunning = true; // 用于标志线程是否继续运行

    @Override
    public void run() {
        while (isRunning) {
            // 执行线程的任务
            try {
                // 可能的睡眠操作，需要注意InterruptedException
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 线程被中断，可以根据需要处理中断事件
                System.out.println("Thread interrupted.");
                // 这里可以进行清理和退出的操作
                isRunning = false; // 设置标志以退出循环
                return; // 退出线程
            }
        }
    }

    // 方法用于停止线程
    public void stopThread() {
        isRunning = false;
        this.interrupt(); // 中断线程，触发InterruptedException
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread  = new MyThread();
        myThread.start();
        Thread.sleep(1000);
        //myThread.stopThread();
        myThread.interrupt();
    }
}

