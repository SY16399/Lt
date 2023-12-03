package org.example.Mutilethread.Test6;

public class twoObjectTwoLock {
    static class HasSelfPrivateNum{
        synchronized public void testMethod(){
            try {
                System.out.println(Thread.currentThread().getName()+" begin "+System.currentTimeMillis());
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName()+" end "+System.currentTimeMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        /*
        上面的代码中有同步方法 testMethod,说明此方法正常情况下应该被顺序调用。
         */
    }
    static class ThreadA extends  Thread{
        private HasSelfPrivateNum numself;

        public ThreadA(HasSelfPrivateNum numself) {
            super();
            this.numself = numself;
        }

        @Override
        public void run() {
            super.run();
            numself.testMethod();
        }
    }
    static class ThreadB extends  Thread{
        private HasSelfPrivateNum numself;

        public ThreadB(HasSelfPrivateNum numself) {
            super();
            this.numself = numself;
        }

        @Override
        public void run() {
            super.run();
            numself.testMethod();
        }
    }

    public static void main(String[] args) {
        HasSelfPrivateNum num1 = new HasSelfPrivateNum();
        HasSelfPrivateNum num2 = new HasSelfPrivateNum();
        ThreadA threadA = new ThreadA(num1);
        threadA.start();
        ThreadB threadB = new ThreadB(num2);
        threadB.start();
    }

}
