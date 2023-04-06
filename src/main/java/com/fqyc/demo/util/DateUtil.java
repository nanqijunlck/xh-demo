package com.fqyc.demo.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.*;

public class DateUtil {
    public static final String EN_YEAR_MONTH_DAY_FORMAT = "yyyyMMdd";
    public static final String CN_LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String CN_YEAR_MONTH_FORMAT = "yyyy-MM";
    public static final String CN_YEAR_MONTH_DAY_FORMAT = "yyyy-MM-dd";

    public static String getTodayString() {
        return format(new Date(), "yyyyMMdd");
    }

    public static String getTodayYMDHMSString() {
        Date date = new Date();

        String yyyyMMdd = format(date, "yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hh = calendar.get(Calendar.HOUR_OF_DAY);
        int mm = calendar.get(Calendar.MINUTE);
        int ss = calendar.get(Calendar.SECOND);
        return yyyyMMdd + hh + mm + ss;
    }

    public static String getTomorrowString() {
        return format(addDay(new Date(), 1), "yyyyMMdd");
    }

    public static String formatSimpleDate(Date date) {
        return format(date, "yyyyMMdd");
    }

    public static Date parseSimpleDate(String dateString)
            throws ParseException {
        return parseDate(dateString, "yyyyMMdd");
    }

    public static Date addMonth(Date date, int amount) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        c.add(2, amount);
        return c.getTime();
    }

    public static Date addDay(Date date, int amount) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(date);
        c.add(5, amount);
        return c.getTime();
    }

    public static Date getNow() {
        return new Date(System.currentTimeMillis());
    }

    public static long to1970(Date time) {
        return time.getTime() / 1000L;
    }

    public static long getNowSeconds() {
        return to1970(getNow());
    }

    public static Date parseDate(String dateText, String format) {
        Date date = null;
        try {
            date = DateUtils.parseDate(dateText, new String[]{format});
        } catch (ParseException e) {
            String error = String.format("转换日期失败：%s,格式:%s", new Object[]{dateText, format});
            throw new IllegalArgumentException(error, e);
        }
        return date;
    }

    public static Date parseDate(String dateText) {
        if (dateText == null) {
            return null;
        }
        String format = "yyyy-MM-dd HH:mm:ss";
        int yearLength = 4;
        int monthLength = 7;
        int dayLength = 10;
        if (dateText.length() == yearLength) {
            format = "yyyy";
        } else if (dateText.length() == monthLength) {
            format = "yyyy-MM";
        } else if (dateText.length() == dayLength) {
            format = "yyyy-MM-dd";
        }
        try {
            return parseDate(dateText, format);
        } catch (Exception e) {
            String error = String.format("转换日期失败：%s,格式:%s", new Object[]{dateText, format});
            throw new IllegalArgumentException(error, e);
        }
    }

    public static Date getBeginOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getBeginOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getBeginOfMonth(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getBeginOfWeek(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.setFirstDayOfWeek(2);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.set(7, calendar.getFirstDayOfWeek());
        return calendar.getTime();
    }

    public static Date getEndOfWeek(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.setFirstDayOfWeek(2);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 0);
        calendar.set(7, calendar.getFirstDayOfWeek() + 6);
        return calendar.getTime();
    }

    public static long getDayBetweenDates(Date s, Date e) {
        if ((s == null) || (e == null)) {
            return 0L;
        }
        long sl = s.getTime();
        long el = e.getTime();
        long hour = (el - sl) / 1000L / 60L / 60L / 24L;
        return Math.abs(hour);
    }

    public static long getBeginOfMonth(long second) {
        Date date = new Date(second * 1000L);
        date = getBeginOfMonth(date);
        return date.getTime() / 1000L;
    }

    public static Date getEndOfMonth(Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(2, 1);
        calendar.add(14, -1);
        return calendar.getTime();
    }

    public static long getEndOfMonth(long second) {
        Date date = new Date(second * 1000L);
        date = getEndOfMonth(date);
        return to1970(date);
    }

    public static int getDatePart(Date time, int field) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(time);
        return calendar.get(field);
    }

    public static Date changeDate(Date time, int amount, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static long add(long second, int amount, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(second * 1000L));
        calendar.add(field, amount);
        return to1970(calendar.getTime());
    }

    public static Date add(Date date, int amount, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static long addToSecond(String date, int amount, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(date));
        calendar.add(field, amount);
        return to1970(calendar.getTime());
    }

    public static Date formatMinute(Date time, int value) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(time);
        int temp = c.get(12);
        int second = 60;
        for (int i = 1; value * i <= second; i++) {
            if (i == 1) {
                if ((temp >= 0) && (temp < value)) {
                    c.set(12, 0);
                }
            } else {
                int t1 = value * (i - 1);
                int t2 = value * i;
                if ((temp >= t1) && (temp < t2)) {
                    c.set(12, t1);
                }
            }
        }
        c.set(13, 0);

        return c.getTime();
    }

    public static long formatMinute(long second, int value) {
        Date d = new Date(second * 1000L);
        return formatMinute(d, value).getTime() / 1000L;
    }

    public static String get24TimesOfDay(Date date) {
        StringBuilder rv = new StringBuilder();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayHour = 24;
        for (int i = 0; i < dayHour; i++) {
            c.set(11, i);
            c.set(12, 0);
            c.set(13, 0);
            String t = String.valueOf(to1970(c.getTime()));
            rv.append(t);
            if (i < 23) {
                rv.append(",");
            }
        }
        return rv.toString();
    }

    public static long[] get12MonthsOfYear(long second) {
        Date d = new Date(second * 1000L);
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        long[] l = new long[12];
        int monthYear = 12;
        for (int i = 0; i < monthYear; i++) {
            c.set(2, i);
            c.set(5, 1);
            c.set(11, 0);
            c.set(12, 0);
            c.set(13, 0);
            l[i] = to1970(c.getTime());
        }
        return l;
    }

    public static long getEndOfYear(long second) {
        long[] t1 = get12MonthsOfYear(second);
        long t2 = t1[(t1.length - 1)];
        long t3 = getEndOfMonth(t2);
        return t3;
    }

    public static long getBeginOfYear(long second) {
        long[] t1 = get12MonthsOfYear(second);
        long t2 = t1[0];
        long t3 = getBeginOfMonth(t2);
        return t3;
    }

    public static String get24TimesOfDay(String second) {
        long d = Long.parseLong(second) * 1000L;
        return get24TimesOfDay(d);
    }

    public static long[] getDaysOfMonth(long second) {
        Date d = new Date(second * 1000L);
        Date bd = getBeginOfMonth(d);
        Date ed = getEndOfMonth(d);

        List l = new ArrayList();
        for (int i = 0; bd.before(ed); i++) {
            l.add(Long.valueOf(bd.getTime() / 1000L));
            bd = add(bd, 1, 5);
        }
        long[] ll = new long[l.size()];
        for (int y = 0; y < l.size(); y++) {
            ll[y] = Long.parseLong(String.valueOf(l.get(y)));
        }
        return ll;
    }

    public static String get24TimesOfDay(long second) {
        return get24TimesOfDay(new Date(second * 1000L));
    }

    public static long[] get24TimesArrOfDay(long second) {
        String ts = get24TimesOfDay(second);
        String[] ta = ts.split(",");
        long[] l = new long[ta.length];
        for (int i = 0; i < ta.length; i++) {
            l[i] = Long.parseLong(ta[i]);
        }
        return l;
    }

    public static long getBeginOfDay(long second) {
        long[] l = get24TimesArrOfDay(second);
        return l[0];
    }

    public static long getEndOfDay(long second) {
        long d = second * 1000L;
        Date date = new Date(d);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(11, 23);
        c.set(12, 59);
        c.set(13, 59);
        return c.getTimeInMillis() / 1000L;
    }

    public static long getNextMon(long second) {
        long[] l = getDaysOfMonth(second);
        return second + 86400 * l.length;
    }

    public static long getNextYear(long second) {
        long beginOfyear = getBeginOfYear(second);
        long tem = beginOfyear;
        int monthYear = 12;
        for (int i = 0; i < monthYear; i++) {
            tem = getNextMon(tem);
        }
        return tem;
    }

    public static long getNextDay(long second) {
        return second + 86400L;
    }

    public static Date getDateNearDetMinute(Date date, int detMinute, boolean before) {
        int dateMinute = 1440;
        if ((date == null) || (detMinute <= 0) || (detMinute >= dateMinute)) {
            return null;
        }
        long result = getBeginOfDay(date).getTime();
        long det = (date.getTime() - result) / 1000L;
        if (!before) {
            det += detMinute * 60 - 1;
        }
        double num = det / 60.0D / detMinute;
        result += (int) num * detMinute * 60 * 1000;
        return new Date(result);
    }

    public static String getWeekOfDate(Date date) {
        String weekDay = "";
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        if (date == null) {
            weekDay = "--";
        } else {
            cal.setTime(date);
            int w = cal.get(7) - 1;
            if (w < 0) {
                w = 0;
            }
            weekDay = weekDays[w];
        }
        return weekDay;
    }

    public static String getMonthChineseDescOfDate(Date date) {
        String month = "";
        String[] days = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};

        Calendar cal = Calendar.getInstance();
        if (date == null) {
            month = "--";
        } else {
            cal.setTime(date);
            int w = cal.get(2);
            if (w < 0) {
                w = 0;
            }
            month = days[w];
        }
        return month;
    }

    public static long getHoursBetweenDates(Date s, Date e) {
        if ((s == null) || (e == null)) {
            return 0L;
        }
        long sl = s.getTime();
        long el = e.getTime();
        long hour = (el - sl) / 1000L / 60L / 60L;
        return Math.abs(hour);
    }

    public static int getMonthBetweenDates(Date s, Date e) {
        Calendar sCalendar = Calendar.getInstance(Locale.CHINA);
        sCalendar.setTime(s);

        Calendar eCalendar = Calendar.getInstance(Locale.CHINA);
        eCalendar.setTime(e);
        return sCalendar.get(2) - eCalendar.get(2);
    }

    public static long getMinsBetweenDates(Date s, Date e) {
        if ((s == null) || (e == null)) {
            return 0L;
        }
        long sl = s.getTime();
        long el = e.getTime();
        long hour = (el - sl) / 1000L / 60L;
        return Math.abs(hour);
    }

    public static boolean isToday(Date day) {
        Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        Calendar td = Calendar.getInstance();
        return (tc.get(1) == td.get(1)) &&
                (tc.get(2) == td.get(2)) &&
                (tc.get(5) == td.get(5));
    }

    public static boolean isTheSameDay(Date day, Date day1) {
        Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        Calendar td = Calendar.getInstance();
        td.setTime(day1);
        return (tc.get(1) == td.get(1)) &&
                (tc.get(2) == td.get(2)) &&
                (tc.get(5) == td.get(5));
    }

    public static boolean isTheSameMonth(Date day, Date day1) {
        Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        Calendar td = Calendar.getInstance();
        td.setTime(day1);
        return (tc.get(1) == td.get(1)) &&
                (tc.get(2) == td.get(2));
    }

    public static boolean isGreaterOrEquToday(Date day) {
        Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        Calendar td = Calendar.getInstance();
        td.set(11, 0);
        td.set(12, 0);
        td.set(13, 0);
        td.set(14, 0);
        return (tc.after(td)) || (tc.equals(td));
    }

    public static boolean isGreaterOrEquThisMonth(Date day) {
        Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        tc.set(5, 1);
        tc.set(11, 0);
        tc.set(12, 0);
        tc.set(13, 0);
        tc.set(14, 0);
        Calendar td = Calendar.getInstance();
        td.set(5, 1);
        td.set(11, 0);
        td.set(12, 0);
        td.set(13, 0);
        td.set(14, 0);
        return (tc.after(td)) || (tc.equals(td));
    }

    public static boolean isLessThanToday(Date day) {
        Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        Calendar td = Calendar.getInstance();
        td.set(11, 0);
        td.set(12, 0);
        td.set(13, 0);
        td.set(14, 0);
        return tc.before(td);
    }

    public static boolean isLessThanThisMonth(Date day) {
        Calendar tc = Calendar.getInstance();
        tc.setTime(day);
        tc.set(5, 1);
        tc.set(11, 0);
        tc.set(12, 0);
        tc.set(13, 0);
        tc.set(14, 0);
        Calendar td = Calendar.getInstance();
        td.set(5, 1);
        td.set(11, 0);
        td.set(12, 0);
        td.set(13, 0);
        td.set(14, 0);
        return tc.before(td);
    }

    public static boolean isThisWeek(Date day) {
        Date now = getNow();
        Date ws = getBeginOfWeek(now);
        Date we = getEndOfWeek(now);
        return ((day.after(ws)) && (day.before(we))) || (day.equals(ws)) || (day.equals(we));
    }

    public static boolean isThisMonth(Date day) {
        Date now = getNow();
        Date ws = getBeginOfMonth(now);
        Date we = getEndOfMonth(now);
        return ((day.after(ws)) && (day.before(we))) || (day.equals(ws)) || (day.equals(we));
    }

    public static int gaps(long srcSeconds, long desSeconds, int type, int minuteStep) {
        int gap = 0;
        Calendar srcCalendar = secondsToCalendar(srcSeconds);
        Calendar desCalendar = secondsToCalendar(desSeconds);
        switch (type) {
            case 1:
                gap = fieldOfCalendar(srcCalendar, 1) - fieldOfCalendar(desCalendar, 1);
                break;
            case 2:
                int year = fieldOfCalendar(srcCalendar, 1) - fieldOfCalendar(desCalendar, 1);

                int month = fieldOfCalendar(srcCalendar, 2) - fieldOfCalendar(desCalendar, 2);
                gap = year * 12 + month;
                break;
            case 5:
                firstCalendar(srcCalendar, 5);
                firstCalendar(desCalendar, 5);
                gap = (int) ((srcCalendar.getTimeInMillis() - desCalendar.getTimeInMillis()) / 86400000L);

                break;
            case 10:
                gap = (int) ((srcSeconds - desSeconds) / 3600L);
                break;
            case 12:
                gap = (int) ((srcSeconds - desSeconds) / (60 * minuteStep));
                break;
        }
        return gap < 0 ? gap * -1 : gap;
    }

    public static int firstCalendar(Calendar calendar, int type) {
        int step = 0;
        switch (type) {
            case 1:
                step = fieldOfCalendar(calendar, 2) - 1;
                calendar.set(2, 0);
                calendar.set(5, 1);
                calendar.set(11, 0);
                calendar.set(12, 0);
                calendar.set(13, 0);
                break;
            case 2:
                step = fieldOfCalendar(calendar, 5) - 1;
                calendar.set(5, 1);
                calendar.set(11, 0);
                calendar.set(12, 0);
                calendar.set(13, 0);
                break;
            case 5:
                step = fieldOfCalendar(calendar, 11);
                calendar.set(11, 0);
                calendar.set(12, 0);
                calendar.set(13, 0);
                break;
            case 11:
                step = fieldOfCalendar(calendar, 12);
                calendar.set(12, 0);
                calendar.set(13, 0);
                break;
        }
        return step;
    }

    public static Calendar secondsToCalendar(long srcSeconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(srcSeconds * 1000L);
        return cal;
    }

    public static long firstSeconds(long seconds, int type) {
        Calendar c = secondsToCalendar(seconds);
        firstCalendar(c, type);
        return calendarToSeconds(c);
    }

    public static long calendarToSeconds(Calendar calendar) {
        long millis = calendar.getTimeInMillis();
        return genSeconds(millis);
    }

    public static long genSeconds(long millis) {
        long seconds = millis / 1000L;
        return seconds;
    }

    public static int fieldOfCalendar(Calendar calendar, int type) {
        int field = -1;
        switch (type) {
            case 1:
                field = calendar.get(1);
                break;
            case 2:
                field = calendar.get(2) + 1;
                break;
            case 5:
                field = calendar.get(5);
                break;
            case 11:
                field = calendar.get(11);
                break;
            case 12:
                field = calendar.get(12);
                break;
            case 13:
                field = calendar.get(13);
                break;
            case 14:
                field = calendar.get(14);
                break;
        }
        return field;
    }

    public static long daysBetween(Date smdate, Date bdate) {
        String format = "yyyy-MM-dd";
        smdate = parseDate(format(smdate, format), format);
        bdate = parseDate(format(bdate, format), format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / 86400000L;
        return betweenDays;
    }

    public static long getMonth(String startDate, String endDate) {
        String format = "yyyy-MM-dd";
        Date startDate1 = parseDate(startDate, format);

        Date endDate1 = parseDate(endDate, format);

        Calendar starCal = Calendar.getInstance();
        starCal.setTime(startDate1);

        int sYear = starCal.get(1);
        int sMonth = starCal.get(2);
        int sDay = starCal.get(5);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate1);
        int eYear = endCal.get(1);
        int eMonth = endCal.get(2);
        int eDay = endCal.get(5);

        long monthday = (eYear - sYear) * 12 + (eMonth - sMonth);
        if (sDay < eDay) {
            monthday += 1L;
        }
        return monthday;
    }

    public static Date addYear(Date date, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(1, year);
        return cal.getTime();
    }

    public static int getCountDayInMonth(Date month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(month);
        return cal.getActualMaximum(5);
    }

    public static String format(Date date, String format) {
        return DateFormatUtils.format(date, format, Locale.CHINA);
    }

    public static String format(Long second, String format) {
        if (format == null) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        Date date = new Date(second.longValue() * 1000L);
        return format(date, format);
    }

    public static Date getNextMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(2, 1);
        return cal.getTime();
    }

    public static String getYesterdayString() {
        return format(addDay(new Date(), 1), "yyyyMMdd");
    }

    public static String getHalfYearDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -6);
        return format(cal.getTime(), CN_YEAR_MONTH_DAY_FORMAT);
    }
}