package org.example.Mutilethread.Test9;

import java.text.SimpleDateFormat;

public class Test {
    static class MyClass{
    }
    public static void main(String[] args) {
        MyClass myClass1 = new MyClass();
        System.out.println(MyClass.class);
        System.out.println(myClass1.getClass());
        System.out.println(MyClass.class.equals(myClass1.getClass()));
    }
}
