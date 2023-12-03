package org.example.singleton.singleton_7;

public class MyObject {
    private static class MyobjectHandler {
        private static MyObject myObject = new MyObject();
    }

    private MyObject() {
    }

    public static MyObject getInstance() {
        return MyobjectHandler.myObject;
    }
}
