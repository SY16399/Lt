package org.example.Pipe;

public class wait_notify_insert_test {
    static class DBTools {
        //变量 prevIsA 的主要作用就是确保备份 ”★★★★★“ 数据库首先执行,然后与 ”☆☆☆☆☆“ 数据库B交替备份。
        volatile private boolean prevIsA = false;

        synchronized public void backupA() {
            try {
                while (prevIsA) {
                    wait();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("★★★★★");
                }
                prevIsA = true;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized public void backupB() {
            try {
                while(!prevIsA){
                    wait();
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("☆☆☆☆☆");
                }
                prevIsA = false;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class BackupA extends Thread{
        private DBTools dbTools;

        public BackupA(DBTools dbTools) {
            super();
            this.dbTools = dbTools;
        }

        @Override
        public void run() {
            dbTools.backupA();
        }
    }
    static class BackupB extends Thread{
        private DBTools dbTools;

        public BackupB(DBTools dbTools) {
            super();
            this.dbTools = dbTools;
        }

        @Override
        public void run() {
            dbTools.backupB();
        }
    }

    public static void main(String[] args) {
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 10; i++) {
            BackupB Bout = new BackupB(dbTools);
            Bout.start();
            BackupA Aout = new BackupA(dbTools);
            Aout.start();
        }
    }
}
