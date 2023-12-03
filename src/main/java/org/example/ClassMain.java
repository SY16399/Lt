package org.example;

public class ClassMain {
    static class MyObject{
        private MyObject myObject;

        public MyObject() {
            this.myObject = new MyObject();
            System.out.println(666);
        }
    }

    public static void main(String[] args) {
        MyObject object = new MyObject();
        object.myObject = null;
    }
}
