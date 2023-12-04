package org.example.Collection;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class Concurrent_SkipListMap {
    static class Userinfo implements Comparable<Userinfo>{
        private int id;
        private String username;

        public Userinfo(int id, String username) {
            this.id = id;
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public int compareTo(Userinfo o) {
            if (this.getId() > o.getId()){
                return 1;
            }else {
                return -1;
            }
        }
    }
    static class MyService{
        private ConcurrentSkipListMap<Userinfo,String> map = new ConcurrentSkipListMap<>();

        public MyService() {
            Userinfo userinfo1 = new Userinfo(1,"userinfo1");
            Userinfo userinfo3 = new Userinfo(3,"userinfo3");
            Userinfo userinfo5 = new Userinfo(5,"userinfo5");
            Userinfo userinfo2 = new Userinfo(2,"userinfo2");
            Userinfo userinfo4 = new Userinfo(4,"Userinfo4");
            map.put(userinfo1,"u1");
            map.put(userinfo3,"u3");
            map.put(userinfo5,"u5");
            map.put(userinfo2,"u2");
            map.put(userinfo4,"u4");
        }
        public void testMethod(){
            Map.Entry<Userinfo,String> entry = map.pollFirstEntry();
            System.out.println("map.size()="+map.size());
            Userinfo userinfo = entry.getKey();
            System.out.println(
                    userinfo.getId()+" "+userinfo.getUsername()+" "
                            +map.get(userinfo)+" "+entry.getValue()
            );
        }
    }
    static class MyThread extends Thread{
        private MyService myService;

        public MyThread(MyService myService) {
            this.myService = myService;
        }

        @Override
        public void run() {
            myService.testMethod();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();
        MyThread a1 = new MyThread(service);
        MyThread a2 = new MyThread(service);
        MyThread a3 = new MyThread(service);
        MyThread a4 = new MyThread(service);
        MyThread a5 = new MyThread(service);
        a1.start();
        Thread.sleep(1000);
        a2.start();
        Thread.sleep(1000);
        a3.start();
        Thread.sleep(1000);
        a4.start();
        Thread.sleep(1000);
        a5.start();
    }

}
