package org.example.Mutilethread.test;

import org.example.WUYONG.MyThread;

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(4000);

    }
}
