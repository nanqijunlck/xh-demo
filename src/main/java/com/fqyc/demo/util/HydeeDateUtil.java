package com.fqyc.demo.util;

import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author stiven_wu
 * 日期处理工具类
 */
public class HydeeDateUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static Date dateFormater(Date date, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return string2Date(format, pattern);
    }


    public static Date string2Date(String dateStr, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateStr);
        return date;
    }

    /**
     * 计算两者日期相距多少天
     * 只按YMD进行计算，不含时分秒
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static Long getHowLongDay(Date startTime, Date endTime) {
        //开始时间在结束时间前 ? 1 : 开始时间与结束时间相等 ? 0 : -1
        int cp = endTime.compareTo(startTime);
        if (-1 == cp) {
            return 0L;
        }
        long betweenDay = DateUtil.betweenDay(startTime, endTime, true);
        //如果相同天，则认为是一天
        return betweenDay + 1L;
    }

    public static void main(String[] args) throws ParseException {
        String startTime = "2021-07-08 14:30:00";
        String endTime = "2021-07-10 11:30:00";

        System.out.println(getHowLongDay(string2Date(startTime, DATE_FORMAT), string2Date(endTime, DATE_FORMAT)));

        Date addTime = new Date();
        Date unBindTime = DateUtil.offsetDay(new Date(), 1);
        Long durationDay = 0L;
        try {
            durationDay = HydeeDateUtil.getHowLongDay(HydeeDateUtil.dateFormater(addTime, HydeeDateUtil.DATE_FORMAT),
                    HydeeDateUtil.dateFormater(unBindTime, HydeeDateUtil.DATE_FORMAT));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(durationDay);
    }
}
