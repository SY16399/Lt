package org.example.singleton.singleton_7_1;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class MyObject implements Serializable {
    private static final long serialVersionUID = 888L;
    public static Userinfo userinfo = new Userinfo();
    private static MyObject myObject = new MyObject();

    private MyObject() {
    }

    public static MyObject getInstance() {
        return myObject;
    }

    protected Object readResolve() throws ObjectStreamException {
        System.out.println("调用了 readResolve方法！");
        return MyObject.myObject;
    }
}
