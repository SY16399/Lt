package org.example.WUYONG;

public class text4 {
    public void a() {
        b();
    }

    public void b() {
        c();
    }

    public void c() {
        d();
    }

    private void d() {
        e();
    }

    private void e() {
       /* StackTraceElement[] array = Thread.currentThread().getStackTrace();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                StackTraceElement eachElement = array[i];
                System.out.println(eachElement.getClassName() + " " + eachElement.getMethodName() + " " + eachElement.getLineNumber());
            }
        }*/
        Thread.dumpStack();
        Thread.getAllStackTraces();
    }

    public static void main(String[] args) throws InterruptedException {
        text4 text4 = new text4();
        text4.a();
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println(123);
                if (Thread.currentThread().interrupted()){
                   // throw new RuntimeException("break");
                }
                System.out.println(Thread.currentThread().isInterrupted());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                   return;
                }
                text4.a();
            }
        });
        thread.start();
        thread.interrupt();
        thread.join();
       /* while (true)
        System.out.println("123456789"+ Thread.interrupted());*/
    }

}
