package org.example.InheritableThreadLocal;

public class Test {
    static class Userinfo{
        public String username;

        public Userinfo(String username) {
            super();
            this.username = username;
        }
    }
    public static void main(String[] args) {
        Userinfo userinfo1 = new Userinfo("我是旧值");
        Userinfo userinfo2 = userinfo1;
        System.out.println(userinfo1.username+" "+userinfo2.username);
        userinfo1.username = "我是新值";
        System.out.println(userinfo1.username+" "+userinfo2.username);
    }
}
