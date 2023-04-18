package com.fqyc.demo.util;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author lck
 * @date 2020-04-23 17:13
 * @since 1.0
 */
public class TimeConditionUtils {
    public static final String LAST_WEEK = "LAST_WEEK";
    public static final String LAST_MONTH = "LAST_MONTH";
    public static final String LAST_YEAR = "LAST_YEAR";
    public static final String CN_MINUTS_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String CN_MINUTSs_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static TimeRange getTimeRange(String timeCondition) {
        Assert.isTrue(!StringUtils.isEmpty(timeCondition), "时间选择条件不能为空");
        int intervalOfDay = timeCondition.equalsIgnoreCase(LAST_YEAR) ? -366 :
                timeCondition.equalsIgnoreCase(LAST_MONTH) ? -31 : -8;

        TimeRange timeRange = new TimeRange();
        Date endDate = DateUtils.ceiling(new Date(System.currentTimeMillis()), Calendar.DATE);
        Date startDate = DateUtils.addDays(endDate, intervalOfDay);
        timeRange.setStartDateTime(startDate);
        timeRange.setEndDateTime(endDate);
        return timeRange;
    }

    public static class TimeRange {

        private Date startDateTime;
        private Date endDateTime;

        public Date getStartDateTime() {
            return startDateTime;
        }

        public void setStartDateTime(Date startDateTime) {
            this.startDateTime = startDateTime;
        }

        public Date getEndDateTime() {
            return endDateTime;
        }

        public void setEndDateTime(Date endDateTime) {
            this.endDateTime = endDateTime;
        }

    }

    /**
     * 日期转换成日的数字，20200601
     *
     * @param date 日期
     * @return 20200601
     */
    public static int date2Int(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        return c.get(Calendar.YEAR) * 10000 + (c.get(Calendar.MONTH) + 1) * 100 + c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期转换成月的数字，202006
     *
     * @param date 日期
     * @return 202006
     */
    public static int date2IntMonth(Date date) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        return c.get(Calendar.YEAR) * 100 + (c.get(Calendar.MONTH) + 1);
    }

    /**
     * 把日的数字转换成日期
     *
     * @param day 20200601
     * @return 日期
     */
    public static Date int2Date(int day) {
        return DateUtil.parse(day + "", "yyyyMMdd");
    }

    /**
     * 把日的数字转换成月
     *
     * @param month 202006
     * @return 月
     */
    public static Date int2Month(int month) {
        return DateUtil.parse(month + "", "yyyyMM");
    }

    public static Date beforeDate(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(date);
        calendarEnd.set(11, 23);
        calendarEnd.set(12, 59);
        calendarEnd.set(13, 59);
        calendarEnd.set(14, 0);
        return calendarEnd.getTime();
    }

    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getMonthBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    public static Date getMonthEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    public static String getCurrentYearMonth() {
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        int month = cal.get(Calendar.MONTH) + 1;
        String monthStr = month < 10 ? "0" + month : "" + month;
        return year + monthStr;
    }

    public static String getLastYearMonth() {
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        int month = cal.get(Calendar.MONTH);
        String monthStr = month < 10 ? "0" + month : "" + month;
        return year + monthStr;
    }

    public static Date beforeMoth(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        calendar.add(2, -1);
        return calendar.getTime();
    }

    public static Date addMin(Date start, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        c.add(Calendar.MINUTE, min);
        return c.getTime();
    }

    public static String formatMinutes(Date date){
        return DateFormatUtils.format(date, CN_MINUTS_FORMAT);
    }

    public static Date getMinuteEndTime(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.SECOND, 59);
        return c.getTime();
    }

    public static Date getMonthBeginDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getMonthBeginTime(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1);
    }

    public static Date getMonthEndDay(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return getMonthEndTime(cale.get(Calendar.YEAR), cale.get(Calendar.MONTH) + 1);
    }


    public static void main(String[] args) throws ParseException {
//        int day = 20200601;
//        System.out.println(int2Date(day));
//        DateTime dateTime = DateUtil.parseDate("2020-12-21");
//        String str1 = DateUtil.formatDateTime(getMonthBeginTime(2020, 9));
//        String str2 = DateUtil.formatDateTime(getMonthEndTime(2020, 9));
//
//        System.out.println(str1 + "---" + str2);
//        String str = getCurrentYearMonth();
//        int i = Integer.parseInt(str.substring(0, 4));
//        int y = Integer.parseInt(str.substring(4, 6));
//        System.out.println(i + "--" + y);
//        int i = Integer.parseInt("06");
//        System.out.println(i);
        String ss = DateFormatUtils.format(getMinuteEndTime(new Date()), CN_MINUTSs_FORMAT);
        System.out.println(ss);
    }


}
