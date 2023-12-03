package org.example.Mutilethread.Test6;

import java.util.concurrent.ConcurrentHashMap;

public class Synchronized {
    static class PrivateNum{
        int num = 0;


        synchronized public void addI(String username){


            try {
                //System.err.println("666");
               // int num = 0;
                if (username.equals("a")){
                    num = 100;
                    System.out.println("a set over");
                    Thread.sleep(2000);
                }else {
                    num = 200;
                    System.out.println("b set over");
                }
                System.out.println(username+" num="+num);
            } catch (InterruptedException e) {
                //TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    static class ThreadA extends Thread{
        private PrivateNum numSelf;

        public ThreadA(PrivateNum numSelf) {
            super();
            this.numSelf = numSelf;
        }

        @Override
        public void run() {
            super.run();
            numSelf.addI("a");
        }
    }
    static class ThreadB extends Thread{
        private PrivateNum numSelf;

        public ThreadB(PrivateNum numSelf) {
            super();
            this.numSelf = numSelf;
        }

        @Override
        public void run() {
            super.run();
            numSelf.addI("b");
        }
    }

    public static void main(String[] args) {
        PrivateNum privateNum = new PrivateNum();
        ThreadA threadA = new ThreadA(privateNum);
        threadA.start();
        ThreadB threadB = new ThreadB(privateNum);
        threadB.start();
    }

}
