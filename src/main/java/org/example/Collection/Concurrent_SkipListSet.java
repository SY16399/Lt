package org.example.Collection;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class Concurrent_SkipListSet {
    static class Userinfo implements Comparable<Userinfo>{
        private int id;
        private String username;

        public Userinfo() {
        }

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

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime* result+id;
            result = prime * result +((username == null)?0:username.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj){
                return true;
            }
            if (obj == null){
                return false;
            }
            if (getClass() != obj.getClass()){
                return false;
            }
            Userinfo orther = (Userinfo) obj;
            if (id != orther.id){
                return false;
            }
            if (username == null){
                if (orther.username != null){
                    if (orther.username != null){
                        return false;
                    }
                }
            }else if (!username.equals(orther.username)){
                return false;
            }
            return true;
        }
    }
    static class MyService{
        private ConcurrentSkipListMap<Concurrent_SkipListMap.Userinfo,String> map = new ConcurrentSkipListMap<>();

        public MyService() {
            Concurrent_SkipListMap.Userinfo userinfo1 = new Concurrent_SkipListMap.Userinfo(1,"userinfo1");
            Concurrent_SkipListMap.Userinfo userinfo3 = new Concurrent_SkipListMap.Userinfo(3,"userinfo3");
            Concurrent_SkipListMap.Userinfo userinfo5 = new Concurrent_SkipListMap.Userinfo(5,"userinfo5");
            Concurrent_SkipListMap.Userinfo userinfo2 = new Concurrent_SkipListMap.Userinfo(2,"userinfo2");
            Concurrent_SkipListMap.Userinfo userinfo4 = new Concurrent_SkipListMap.Userinfo(4,"Userinfo4");
            map.put(userinfo1,"u1");
            map.put(userinfo3,"u3");
            map.put(userinfo5,"u5");
            map.put(userinfo2,"u2");
            map.put(userinfo4,"u4");
        }
        public void testMethod(){
            Map.Entry<Concurrent_SkipListMap.Userinfo,String> entry = map.pollFirstEntry();
            System.out.println("map.size()="+map.size());
            Concurrent_SkipListMap.Userinfo userinfo = entry.getKey();
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
