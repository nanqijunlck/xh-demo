package com.fqyc.demo.util;

import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Utils {

    public static List<Date> getNearHoursStamp(int count) {
        Date now = new Date();
        List<Date> dates = Lists.newArrayList();
        for (int i = count; i >= 0; i--) {
            Date date = DateUtils.addHours(now, 0 - i);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dates.add(calendar.getTime());
        }
        return dates;
    }

    public static List<Date> getNearDaysStamp(int count, boolean hasToday) {
        Date now = new Date();
        List<Date> dates = Lists.newArrayList();
        int tmpCount = count;
        if (count == 0 && hasToday) {
            tmpCount = 1;
        }
        for (int i = tmpCount; i >= 0; i--) {
            Date date = DateUtils.addDays(now, 0 - i);
            if (count == 0 && hasToday) {
                date = new Date();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            if (hasToday && i == 0) {
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
            } else {
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            }
            dates.add(calendar.getTime());
        }
        return dates;
    }

    private static List<Date> getNearMonthsStamp(int count, boolean hasCurMonth) {
        Date now = new Date();
        List<Date> dates = Lists.newArrayList();
        int tmpCount = count;
        if (count == 0 && hasCurMonth) {
            tmpCount = 1;
        }
        for (int i = tmpCount; i >= 0; i--) {
            Date date = DateUtils.addMonths(now, 0 - i);
            if (count == 0 && hasCurMonth) {
                date = new Date();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            //calendar.set(Calendar.DATE, 0);
            if (hasCurMonth && i == 0) {
                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE, 59);
                calendar.set(Calendar.SECOND, 59);
                calendar.set(Calendar.MILLISECOND, 999);
            } else {
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
            }

            dates.add(calendar.getTime());
        }
        return dates;
    }

    @SneakyThrows
    public static Date parse(String date, String format) {
        return new SimpleDateFormat(format).parse(date);
    }

    public static Date onTheHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        return calendar.getTime();
    }

    public static Date onTheDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
    }

    private static Date onTheMonth(Date date) {
        Date date1 = onTheDay(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static List<Date> getDatePoints(TimeUnit timeUnit, Long gteTimestamp, Long ltTimestamp) {
        List<Date> dates = new ArrayList<>();

        if (timeUnit == TimeUnit.HOUR) {
            dates.add(onTheHour(new Date(gteTimestamp)));
            int hours = (int) ((ltTimestamp - gteTimestamp) / (1000 * 3600));
            for (int i = hours; i > 1; i--) {
                dates.add(onTheHour(DateUtils.addHours(new Date(ltTimestamp), 1 - i)));
            }
            dates.add(onTheHour(new Date(ltTimestamp)));
            dates = dates.stream().map(d -> format(d,"yyyy-MM-dd HH")).distinct().map(d -> onTheHour(parse(d, "yyyy-MM-dd HH"))).sorted(Comparator.comparingLong(Date::getTime)).collect(Collectors.toList());
        }

        if (timeUnit == TimeUnit.DAY) {
            dates.add(onTheDay(new Date(gteTimestamp)));
            int days = (int) ((ltTimestamp - gteTimestamp) / (1000 * 3600 * 24L));
            for (int i = days; i > 1; i--) {
                dates.add(onTheDay(DateUtils.addDays(new Date(ltTimestamp), 1 - i)));
            }
            dates.add(onTheDay(new Date(ltTimestamp)));
            dates = dates.stream().map(d -> format(d,"yyyy-MM-dd")).distinct().map(d -> onTheDay(parse(d, "yyyy-MM-dd"))).sorted(Comparator.comparingLong(Date::getTime)).collect(Collectors.toList());
        }

        if (timeUnit == TimeUnit.MONTH) {
            dates.add(onTheMonth(new Date(gteTimestamp)));
            int month = Math.abs((int) ChronoUnit.MONTHS.between(LocalDate.parse(format(new Date(gteTimestamp), "yyyy-MM-dd")), LocalDate.parse(format(new Date(ltTimestamp), "yyyy-MM-dd"))));
            for (int i = month; i > 1; i--) {
                dates.add(onTheMonth(DateUtils.addMonths(new Date(ltTimestamp), 1 - i)));
            }
            dates.add(onTheMonth(new Date(ltTimestamp)));
            dates = dates.stream().map(d -> format(d,"yyyy-MM-dd")).distinct().map(d -> onTheDay(parse(d, "yyyy-MM-dd"))).sorted(Comparator.comparingLong(Date::getTime)).collect(Collectors.toList());
        }
        return dates;
    }

    public static List<Date> getRange(String last, boolean hasCurrent) {
        List<Date> dateRange = null;
        last = last.toUpperCase();
        if (last.contains(TimeUnit.HOUR.name())) {    //小时
            int interval = Integer.parseInt(last.replace(TimeUnit.HOUR.name(), ""));
            dateRange = Utils.getNearHoursStamp(0 - interval);
        }
        if (last.contains(TimeUnit.DAY.name())) {    //天
            int interval = Integer.parseInt(last.replace(TimeUnit.DAY.name(), ""));
            dateRange = Utils.getNearDaysStamp(0 - interval, hasCurrent);
        }
        if (last.contains(TimeUnit.MONTH.name())) {
            int interval = Integer.parseInt(last.replace(TimeUnit.MONTH.name(), ""));
            dateRange = Utils.getNearMonthsStamp(0 - interval, hasCurrent);
        }
        if (dateRange == null) {
            throw new IllegalArgumentException("TimeUnit is invalid");
        }
        return dateRange;
    }

    public static boolean has(Integer value) {
        return value != null && value > 0;
    }

    public static boolean has(Long value) {
        return value != null && value > 0L;
    }

    public static boolean has(String value) {
        return StringUtils.isNotBlank(value);
    }

}
