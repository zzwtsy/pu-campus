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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
     * 补全日期
     *
     * @param date 日期 (yyyy-MM-dd HH:mm:ss)
     * @return {@link String}
     */
    public static String complementaryDate(String date) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + date + ":00";
    }

    /**
     * 检查日期格式
     *
     * @param date 日期
     * @return boolean
     */
    public static boolean checkDateFormat(String date) {
        String dateFormat = "^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$";
        return Pattern.matches(dateFormat, date);
    }
}
