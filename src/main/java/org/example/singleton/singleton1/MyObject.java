package org.example.singleton.singleton1;

public class MyObject {
    private static MyObject myObject;

    public MyObject() {
    }
    public static MyObject getInstance(){
        //延迟加载
        if (myObject != null){
        }else {
            myObject = new MyObject();
        }
        return myObject;
    }
}
