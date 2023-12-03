package org.example.SimpleDataFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Run {
    public static void main(String[] args) throws ParseException {
        String dataString1 = "2000-1-1";
        String dataString2 = "2000-11-18";
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-M-d");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        //先按照日期模式将字符串解析成日期再格式化成时间字符串。
        System.out.println(format1.format(format1.parse(dataString1)));
        System.out.println(format2.format(format2.parse(dataString1)));
        System.out.println(format1.format(format1.parse(dataString2)));
        System.out.println(format2.format(format2.parse(dataString2)));

    }
}
