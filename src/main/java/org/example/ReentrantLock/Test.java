package org.example.ReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock(true);
        lock.lock();
        Condition condition = lock.newCondition();
        System.out.println("await begin");
        condition.await();
        System.out.println("await end");
    }
}
