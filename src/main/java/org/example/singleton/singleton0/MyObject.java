package org.example.singleton.singleton0;
//单例模式
public class MyObject {
    //立即加载方式 == 饿汉模式
    private static MyObject myObject = new MyObject();

    private MyObject() {
    }
    private static String username;
    private static String password;
    public static MyObject getInstance(){
        username = "从不同的服务器取出值（有可能不一样），并赋值";
        password = "从不同的服务器取出值（有可能不一样），并赋值";
        //上面的赋值并没有被同步，所以极易出现非线程安全问题，导致变量值被覆盖。
        return myObject;
    }

}
