package org.example.Mutilethread.Test6;

public class Test2 {
    public void myMethod(){
        synchronized (this){
            int age =10 ;
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Test2 test2 = new Test2();
        test2.myMethod();
    }
}
