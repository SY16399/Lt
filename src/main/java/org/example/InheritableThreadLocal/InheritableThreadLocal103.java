package org.example.InheritableThreadLocal;

public class InheritableThreadLocal103 {
    static class Userinfo{
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    static class Tools{
        public static java.lang.InheritableThreadLocal<Userinfo> t1 = new java.lang.InheritableThreadLocal<>();
    }
    static class ThreadA extends Thread{
        @Override
        public void run() {
            try{
                for (int i = 0; i < 10; i++) {
                    Userinfo userinfo = Tools.t1.get();
                    System.out.println("在ThreadA线程中取值="+userinfo.getUsername()+" "+userinfo.hashCode());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Userinfo userinfo = new Userinfo();
        System.out.println("A userinfo "+userinfo.hashCode());
        userinfo.setUsername("中国");
        if (Tools.t1.get() == null){
            Tools.t1.set(userinfo);
        }
        System.out.println("    在Main线程中取值="+Tools.t1.get().getUsername()+" "+Tools.t1.get().hashCode());
        Thread.sleep(100);
        ThreadA a = new ThreadA();
        a.start();
        Thread.sleep(5000);
        Userinfo userinfo2 = new Userinfo();
        userinfo2.setUsername("美国");
        System.out.println("B userinfo "+userinfo2.hashCode());
        Tools.t1.set(userinfo2);
    }
}
