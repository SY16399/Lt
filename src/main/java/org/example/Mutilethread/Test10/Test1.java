package org.example.Mutilethread.Test10;

public class Test1 {
    public static void main(String[] args) {
        System.out.println("A "+ Thread.holdsLock(Test1.class));
        synchronized (Test1.class) {
            System.out.println("B "+ Thread.holdsLock(Test1.class));
        }
        System.out.println("C "+ Thread.holdsLock(Test1.class));
    }
}
