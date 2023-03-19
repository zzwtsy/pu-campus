package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.data.Setting;
import cn.zzwtsy.pu.service.UserService;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.regex.Pattern;

import static cn.zzwtsy.pu.tools.Consts.DB_FILE_FULL_PATH;
import static cn.zzwtsy.pu.tools.Consts.PLUGIN_DATA_FILE_PATH;
import static cn.zzwtsy.pu.utils.DateUtil.complementaryDate;

/**
 * 拆分消息
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class Tools {
    /**
     * 判断消息是否包含命令
     *
     * @param message 消息
     * @param command 命令数组
     * @return boolean
     */
    public static boolean messageContainsCommand(String message, String[] command) {
        for (String s : command) {
            if (message.equals(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查时间，格式（00-00）
     *
     * @param time 时间
     * @return boolean
     */
    public static boolean checkTime(String time) {
        String timeFormat = "^(?:[01]\\d|2[0-3]):[0-5]\\d$";
        return Pattern.matches(timeFormat, time);
    }

    /**
     * 以空格拆分消息
     *
     * @param message 消息
     * @return {@link String[]}
     */
    public static String[] splitMessage(String message) {
        return message.split(" ");
    }

    /**
     * 拆分消息
     *
     * @param message   消息
     * @param splitChar 分割字符
     * @return {@link String[]}
     */
    public static String[] splitMessage(String message, String splitChar) {
        return message.split(splitChar);
    }

    /**
     * 检查用户是否登录
     *
     * @param qqId 用户qq号
     * @return boolean
     */
    public static boolean checkUserLogin(long qqId) {
        return new UserService().getUser(qqId) == null;
    }

    /**
     * 检查用户qq号是否正确
     *
     * @param qqId qq号
     * @return boolean
     */
    public static boolean checkUserQqId(String qqId) {
        String qqIdFormat = "^[1-9][0-9]{4,10}$";
        return Pattern.matches(qqIdFormat, qqId);
    }

    /**
     * 检查用户是否为管理员
     *
     * @param qqId qq号
     * @return boolean
     */
    public static boolean checkAdminQqId(long qqId) {
        return Setting.INSTANCE.getAdminId().contains(qqId);
    }

    /**
     * 检查数据库文件是否存在,不存在则创建数据库文件夹
     *
     * @return boolean
     */
    public static boolean checkDataBaseFile() {
        if (new File(DB_FILE_FULL_PATH).exists()) {
            return true;
        } else {
            if (new File(PLUGIN_DATA_FILE_PATH).exists()) {
                PuCampus.INSTANCE.getLogger().info("数据库文件夹已存在");
                return false;
            }
            if (new File(PLUGIN_DATA_FILE_PATH).mkdirs()) {
                PuCampus.INSTANCE.getLogger().info("创建数据库文件夹成功");
                return false;
            } else {
                PuCampus.INSTANCE.getLogger().error("创建数据库文件夹失败");
            }
            return false;
        }
    }

    /**
     * 计算定时任务延迟时间
     *
     * @return long
     * @throws ParseException 解析异常
     */
    public static long calculateScheduledDelayTime() throws ParseException {
        String addDate = complementaryDate(Setting.INSTANCE.getTimedTask());
        return calculateTime(addDate);
    }

    /**
     * 计算定时任务延迟时间
     *
     * @param time 时间 (03:03)
     * @return long
     * @throws ParseException 解析异常
     */
    public static long calculateScheduledDelayTime(String time) throws ParseException {
        String addDate = complementaryDate(time);
        return calculateTime(addDate);
    }

    private static long calculateTime(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        long timedTaskTime = sdf.parse(time).getTime();
        //获取当前时间的时间戳
        long nowTime = System.currentTimeMillis();
        //设定时间减去当前时间并转换为秒
        long delayTime = (timedTaskTime - nowTime) / 1000L;
        //设定时间位于当前时间之后，直接返回延迟时间即可
        if (delayTime > 0) {
            return delayTime;
        } else if (delayTime < 0) {
            //设定时间位于当前时间之前，需要延迟到第二天执行
            //24小时 = 86400秒
            return 86400 + delayTime;
        } else {
            return 0;
        }
    }
}
