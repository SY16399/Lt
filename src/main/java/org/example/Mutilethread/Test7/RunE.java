package org.example.Mutilethread.Test7;

public class RunE {
    static class PublicVar {
        public String usernmae = "A";
        public String password = "AA";

        synchronized public void setValue(String usernmae, String password) {
            try {
                this.usernmae = usernmae;
                Thread.sleep(5000);
                this.password = password;
                System.out.println("setValue method thread name=" +
                        Thread.currentThread().getName() + " username=" +
                        usernmae + " password= " + password);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

         synchronized public void getValue() {
            System.out.println("getValue method thread name="
                    + Thread.currentThread().getName() + " username=" + usernmae
                    + " password=" + password);
        }
    }

    public static class ThreadA extends Thread {
        private PublicVar publicVar;

        public ThreadA(PublicVar publicVar) {
            super();
            this.publicVar = publicVar;
        }

        @Override
        public void run() {
            super.run();
            publicVar.setValue("B", "BB");
        }
    }

    public static void main(String[] args) {
        try {
            PublicVar publicVar = new PublicVar();
            ThreadA threadA = new ThreadA(publicVar);
            threadA.start();
            Thread.sleep(200);//结果受此值大小影响
            publicVar.getValue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
