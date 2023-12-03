package org.example.Mutilethread.Test5;

public class RunW {
    static class MyObject{
        private String username = "1";
        private String password = "11";
        public void  setValue(String u,String p){
            this.username = u;
            if (Thread.currentThread().getName().equals("a")){
                System.out.println("停止线程A");
                Thread.currentThread().suspend();
            }
            this.password = p;
        }
        public void printUsernamePassword(){
            System.out.println(username+" "+password);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final MyObject myObject = new MyObject();
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                myObject.setValue("a","aa");
            }
        };
        thread1.setName("a");
        thread1.start();
        Thread.sleep(500);
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                myObject.printUsernamePassword();
            }
        };
        thread2.start();
    }


}
