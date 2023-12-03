package org.example.singleton.singleton_2;

public class MyObject {
    private static MyObject myObject;
    private MyObject(){
    }
    //设置同步方法效率太低
    //整个方法被上锁
    public static MyObject getInstance(){
        try {
                if (myObject != null){
                }else {
                    //模拟创建对象之前做一些准备工作
                    Thread.sleep(3000);
                    synchronized (MyObject.class) {
                        if (myObject==null){
                            myObject = new MyObject();
                        }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return myObject;
    }
    //此版本的代码称为：
    //双重检查 Double-Check
}
