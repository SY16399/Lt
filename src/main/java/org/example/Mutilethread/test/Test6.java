package org.example.Mutilethread.test;

public class Test6 {
    public static void main(String[] args) {
        Thread runThread = Thread.currentThread();
        System.out.println(runThread.getName()+" "+runThread.getId());
        Thread Thread = new Thread();
        System.out.println(Thread.getName()+" "+Thread.getId());
        Thread Thread1 = new Thread();
        System.out.println(Thread1.getName()+" "+Thread1.getId());
        Thread Thread2 = new Thread();
        System.out.println(Thread2.getName()+" "+Thread2.getId());
        Thread Thread3 = new Thread();
        System.out.println(Thread3.getName()+" "+Thread3.getId());
    }
}
