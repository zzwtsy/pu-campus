package cn.zzwtsy.pu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 日期工具类
 *
 * @author zzwtsy
 * @since 2022/12/25
 */
public class DateUtil {
    /**
     * unix时间戳格式转换
     *
     * @param timestamp 时间戳
     * @return {@link String}
     */
    public static String formatUnixTimestamp(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date(timestamp * 1000L);
        return simpleDateFormat.format(time);
    }

    /**
     * 日期计算
     *
     * @param amount 加减日期天数
     * @return {@link String}
     */
    public static String dateCalculate(int amount) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, amount);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 添加年份
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String addYear(String date) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) + "-" + date;
    }

    /**
     * 检查日期格式
     *
     * @param date 日期
     * @return boolean
     */
    public static boolean checkDateFormat(String date) {
        String dateFormat = "^\\d{1,2}-\\d{1,2}";
        return Pattern.matches(dateFormat, date);
    }
}
