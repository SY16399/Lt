package org.example.Mutilethread.Test10;

public class MyService {
    synchronized public static void Method1(){
    }
    public void Method2(){
        synchronized (MyService.class){
        }
    }
    synchronized public void Method3(){
    }
    public void Method4(){
        synchronized (this){
        }
    }
    public void Method5(){
        synchronized ("abc"){
        }
    }
}
