package com.lry.lostchildinfo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description :
 * @ClassName : TimeUtils
 * @Author : jdl
 * @Create : 2022-04-14 15:46
 */
public class TimeUtils {
    /**
     * 获取前几天的
     * @param beforeDay
     * @return
     */
    public static String[] startTime(Integer beforeDay){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间格式
        Date date = new Date(); //当前时间
        //Date date = sdf.parse("2022-01-22 08:00:22");  //测试数据
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, beforeDay); //设置为前一天
        String beforeOneDay = sdf.format(calendar.getTime()); //得到前一天的时间
        String startTime = beforeOneDay.substring(0, 10) + " 00:00:00";
        String endTime = beforeOneDay.substring(0, 10) + " 23:59:59";
        String[] beforeDayTime = new String[2];
        beforeDayTime[0]=startTime;
        beforeDayTime[1]=endTime;
        return beforeDayTime;
    }

    public static String dateFormat(Integer beforeDay){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间格式
        Date date = new Date(); //当前时间
        //Date date = sdf.parse("2022-01-22 08:00:22");  //测试数据
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, beforeDay); //设置为前一天
        String beforeOneDay = sdf.format(calendar.getTime()); //得到前一天的时间
        return beforeOneDay.substring(0,10);
    }

    public static void main(String[] args) {
//        String[] strings = startTime(0);
//        System.out.println(strings[0]);
//        System.out.println(strings[1]);
        System.out.println(dateFormat(-1));
    }

}
