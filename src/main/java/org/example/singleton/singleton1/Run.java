package org.example.singleton.singleton1;

import org.example.singleton.singleton0.MyThread;

public class Run {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.start();
        MyThread t2 = new MyThread();
        t2.start();
    }
}
