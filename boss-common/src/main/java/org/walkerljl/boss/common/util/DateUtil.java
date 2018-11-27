package org.walkerljl.boss.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * DateUtil
 *
 * @author xingxun
 */
public class DateUtil {

    public static final String FORMAT_YYYYMM     = "yyyyMM";
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    public static final long getTime(Date date) {
        return date == null ? 0 : date.getTime();
    }

    /**
     * 修改时间
     *
     * @param time
     * @param timeUnit
     * @param interval
     * @return
     */
    public static Date modifyTime(Date time, TimeUnit timeUnit, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(time);
        if (timeUnit == TimeUnit.DAYS) {
            calendar.add(Calendar.DAY_OF_MONTH, interval);
        } else if (timeUnit == TimeUnit.HOURS) {
            calendar.add(Calendar.HOUR, interval);
        } else if (timeUnit == TimeUnit.MINUTES) {
            calendar.add(Calendar.MINUTE, interval);
        } else if (timeUnit == TimeUnit.SECONDS) {
            calendar.add(Calendar.SECOND, interval);
        }
        return calendar.getTime();
    }

    public static Date dataFormat2Date(String dateStr, String format) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        if (StringUtil.isEmpty(format)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Date dateFormat2Date(Date date, String format) {
        if (date == null || isStringEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String formatDateString = simpleDateFormat.format(date);
        try {
            return simpleDateFormat.parse(formatDateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将日期对象按照指定格式转换成字符串
     *
     * @param date 日期对象
     * @param format 格式
     * @return
     */
    public static String dateFormat2String(Date date, String format) {
        if (date == null || isStringEmpty(format)) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    private static boolean isStringEmpty(String string) {
        return string == null || "".equals(string.trim());
    }
}