package org.example.Mutilethread.TEST8;

import java.util.ArrayList;
import java.util.List;

public class syn_out_asyn {
    static class MyList{
        private List<String> list = new ArrayList<>();
        synchronized public void add(String  username){
            System.out.println("ThreadName ="+ Thread.currentThread().getName()+" 执行了add方法！");
            list.add(username);
            System.out.println("ThreadName ="+ Thread.currentThread().getName()+" 退出了add方法！");
        }
        synchronized public int getSize(){
            System.out.println("ThreadName= "+Thread.currentThread().getName()+"执行了getsize方法！");
            int setValue = list.size();
            System.out.println("ThreadName= "+Thread.currentThread().getName()+"退出了getsize方法！");
            return setValue;
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
            for (int i = 0; i < 100000; i++) {
                list.add("threadA"+(i+1));
            }
        }
    }
    static class MythreadB extends Thread{
        private MyList list ;

        public MythreadB(MyList list) {
            super();
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                list.add("threadA"+(i+1));
            }
        }
    }

    public static void main(String[] args) {
        MyList list = new MyList();
        MythreadA a = new MythreadA(list);
        a.setName("A");
        a.start();
        MythreadB b = new MythreadB(list);
        b.setName("B");
        b.start();
    }
}
