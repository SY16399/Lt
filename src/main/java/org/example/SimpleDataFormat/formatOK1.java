package org.example.SimpleDataFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class formatOK1 {

    static class DateTools{

        public static Date parse(String formatPattern, String dateString) throws ParseException {
            return new SimpleDateFormat(formatPattern).parse(dateString);
        }


        public static String format(String formatPattern, Date date) {
            return new SimpleDateFormat(formatPattern).format(date);
        }
    }
    static class MyThread extends Thread {
        private SimpleDateFormat sdf;
        private String dateString;

        public MyThread(SimpleDateFormat sdf, String dateString) {
            this.sdf = sdf;
            this.dateString = dateString;
        }

        @Override
        public void run() {
            try {
                Date dateRef = DateTools.parse("yyyy-MM-dd",dateString);
                String newDataString = DateTools.format("yyyy-MM-dd",dateRef);
                if (!newDataString.equals(dateString)) {
                    System.out.println("ThreadName=" + this.getName()
                            + "报错了 日期字符串：" + dateString + " 转换成的日期为："
                            + newDataString);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] dateStringArray = new String[]{"2000-01-01",
                "2000-01-02","2000-01-03","2000-01-04",
                "2000-01-05","2000-01-06","2000-01-07",
                "2000-01-08","2000-01-09","2000-01-10",
        };
        MyThread[] threads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new MyThread(sdf,dateStringArray[i]);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
