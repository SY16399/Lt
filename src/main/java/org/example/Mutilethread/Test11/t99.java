package org.example.Mutilethread.Test11;

public class t99 {
    static class PrintString implements Runnable{
        private boolean isContinuePrint = true;
        public  boolean isContinuePrint(){
            return isContinuePrint;
        }

        public void setContinuePrint(boolean continuePrint) {
            isContinuePrint = continuePrint;
        }
        public void printStringMethod(){
            try {
                while(isContinuePrint){
                    System.out.println("run printStringMethod threadName="+Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            printStringMethod();
        }
    }

    public static void main(String[] args) {
        PrintString printString = new PrintString();
        new Thread(printString).start();
        System.out.println("我要停止它 ！stopThread="+Thread.currentThread().getName());
        printString.setContinuePrint(false);
    }
}
