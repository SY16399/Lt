package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
    private int value;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void setValue(int val) {
        lock.lock();
        try {
            while (value != 0) {
                condition.await();
            }
            value = val;
            System.out.println("Set value: " + value);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getValue() {
        lock.lock();
        try {
            while (value == 0) {
                condition.await();
            }
            int retrievedValue = value;
            System.out.println("Got value: " + retrievedValue);
            value = 0;
            condition.signal();
            return retrievedValue;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        } finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable {
    private Resource resource;

    public Producer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            resource.setValue(i);
        }
    }
}

class Consumer implements Runnable {
    private Resource resource;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            int retrievedValue = resource.getValue();
        }
    }
}

public class HoareExample {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Thread producerThread = new Thread(new Producer(resource));
        Thread consumerThread = new Thread(new Consumer(resource));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
