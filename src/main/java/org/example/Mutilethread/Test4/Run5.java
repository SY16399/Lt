package org.example.Mutilethread.Test4;

public class Run5 {
     static class MyService{
        private String username = "a";
        private String password = "aa";

        synchronized public String getUsername() {
            return username;
        }

        synchronized public String getPassword() {
            return password;
        }
        public void PrintString(String username,String password){
            try {
                this.username = username;
                Thread.sleep(1000000);
                this.password = password;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    static class MyThreadA extends Thread{
        private MyService obj;

        public MyThreadA(MyService obj) {
            super();
            this.obj = obj;
        }

        @Override
        public void run() {
            obj.PrintString("b","bb");
        }
    }
    static class MyThreadB extends Thread{
         private MyService obj;

        public MyThreadB(MyService obj) {
            super();
            this.obj = obj;
        }

        @Override
        public void run() {
            System.out.println(obj.getUsername()+" "+obj.getPassword());
            System.out.println("end "+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThreadA myThreadA = new MyThreadA(myService);
        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadA.start();
        Thread.sleep(500);
        myThreadB.start();
        System.out.println("begin" + System.currentTimeMillis());
        Thread.sleep(5000);
        myThreadA.stop();
    }


}
