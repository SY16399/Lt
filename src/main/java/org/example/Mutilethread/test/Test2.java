package org.example.Mutilethread.test;


public class Test2 {
    static class Mythraed extends Thread{
        @Override
        public void run() {
            System.out.println("Mythraed");
        }
    }
    public static void main(String[] args) {
        Mythraed thread = new Mythraed();
        thread.start();
        System.out.println("运行结束");
    }
}
