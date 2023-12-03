package org.example.Join;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread.currentThread().join(2000,999999);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-begin);
    }
}
