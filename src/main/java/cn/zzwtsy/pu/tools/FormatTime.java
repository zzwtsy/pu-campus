package cn.zzwtsy.pu.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳格式转换
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class FormatTime {
    public static String formatUnixTimestamp(long timestamp) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = new Date(timestamp * 1000L);
        return simpleDateFormat.format(time);
    }
}
