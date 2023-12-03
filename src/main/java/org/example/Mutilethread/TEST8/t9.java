package org.example.Mutilethread.TEST8;

import java.util.ArrayList;
import java.util.List;

public class t9 {
    //只允许装一个的集会
    static class MyList{
        private List<String> list = new ArrayList<>();
        synchronized public void add(String  username){
            list.add(username);
        }
        synchronized public int getSize(){
            return list.size();
        }
    }
    static class MyService{
        public MyList addServiceMethod(MyList list,String data){
            try {
                synchronized (list){
                    if (list.getSize() < 1){
                        Thread.sleep(2000);//模拟从远程花费 2 s 取回数据
                        list.add(data);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return list;
        }
    }
    static class MythreadA extends Thread{
        private MyList list ;

        public MythreadA(MyList list) {
            super();
            this.list = list;
        }

        @Override
        public void run() {
            MyService service = new MyService();
            service.addServiceMethod(list,"A");
        }
    }
    static class MythreadB extends Thread {
        private MyList list;

        public MythreadB(MyList list) {
            super();
            this.list = list;
        }

        @Override
        public void run() {
            MyService service = new MyService();
            service.addServiceMethod(list, "B");
        }


    }
    public static void main(String[] args) throws InterruptedException {
        MyList list = new MyList();
        MythreadA a = new MythreadA(list);
        a.setName("A");
        a.start();
        MythreadB b = new MythreadB(list);
        b.setName("B");
        b.start();
        Thread.sleep(6000);
        System.out.println("listSize="+list.getSize());
    }
}