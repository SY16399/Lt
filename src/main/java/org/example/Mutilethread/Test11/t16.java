package org.example.Mutilethread.Test11;

public class t16 {
    static class RunThread extends Thread{
        volatile private boolean isRuning = true;
        public boolean isRuning(){
            return isRuning;
        }
        public void  setRuning(boolean isRuning){
            this.isRuning = isRuning;
        }

        @Override
        public void run() {
            System.out.println("进入 run 了");
            while(isRuning){

            }
            System.out.println(" 线程被停止了");
        }
    }

    public static void main(String[] args) {
        try{
            RunThread thread = new RunThread();
            thread.start();
            Thread.sleep(1000);
            thread.setRuning(false);
            System.out.println("已经赋值为false");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
