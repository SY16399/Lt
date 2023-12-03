package org.example.Mutilethread.Test7;

public class RunS {
    static class Service{
        synchronized public void service1(){
            System.out.println("service1");
            service2();
        }

        synchronized private void service2() {
            System.out.println("service2");
            service3();
        }

        synchronized private void service3() {
            System.out.println("service3");
        }
    }
    static class Mythread extends Thread{
        @Override
        public void run() {
            Service service = new Service();
            service.service1();
        }
    }

    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        mythread.start();
    }
}
