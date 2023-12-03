package org.example;

class CommunicatingProcess {
    private int message;

    public synchronized void sendMessage(int msg) {
        while (message != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        message = msg;
        System.out.println("Sent message: " + message);
        notify();
    }

    public synchronized int receiveMessage() {
        while (message == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int receivedMsg = message;
        System.out.println("Received message: " + receivedMsg);
        message = 0;
        notify();
        return receivedMsg;
    }
}

class Sender implements Runnable {
    private CommunicatingProcess process;

    public Sender(CommunicatingProcess process) {
        this.process = process;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            process.sendMessage(i);
        }
    }
}

class Receiver implements Runnable {
    private CommunicatingProcess process;

    public Receiver(CommunicatingProcess process) {
        this.process = process;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            int receivedMsg = process.receiveMessage();
        }
    }
}

public class BrinchHansenExample {
    public static void main(String[] args) {
        CommunicatingProcess process = new CommunicatingProcess();
        Thread senderThread = new Thread(new Sender(process));
        Thread receiverThread = new Thread(new Receiver(process));

        senderThread.start();
        receiverThread.start();

        try {
            senderThread.join();
            receiverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}