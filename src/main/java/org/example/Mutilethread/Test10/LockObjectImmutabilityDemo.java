package org.example.Mutilethread.Test10;

public class LockObjectImmutabilityDemo {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        Thread thread1 = new Thread(() -> {
            synchronized (sharedResource) {
                System.out.println("Thread 1: Holding the lock on sharedResource");
                sharedResource.setCounter(10);
                try {
                    Thread.sleep(2000); // Sleep for 2 seconds to simulate some work
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Done with the lock on sharedResource");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (sharedResource) {
                System.out.println("Thread 2: Holding the lock on sharedResource");
                sharedResource.setCounter(20);
                System.out.println("Thread 2: Done with the lock on sharedResource");
            }
        });

        thread1.start();
        thread2.start();
    }
}

class SharedResource {
    private int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

