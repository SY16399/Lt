package org.example.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class isFair {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock(true);
        System.out.println(lock1.isFair());
        ReentrantLock lock2 = new ReentrantLock(false);
        System.out.println(lock2.isFair());
        ReentrantLock lock3 = new ReentrantLock();
        System.out.println(lock3.isFair());
    }
}
