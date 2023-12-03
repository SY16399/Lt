package org.example;

public class Test {
    //临界区开始
    synchronized public void test1(){
        //代码1
        //代码2
        //代码3
    }
    //临界区结束

    public void test2(){
        //临界区开始
        synchronized (this){
            //代码1
            //代码2
            //代码3
        }
        //临界区结束
    }

    public static void main(String[] args) {
        System.out.println(Test.class);
        System.out.println(Test.class.getClass());
        System.out.println(Test.class.getClass().getClass());
    }
}
