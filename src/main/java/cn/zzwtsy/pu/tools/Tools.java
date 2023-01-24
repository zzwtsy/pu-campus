package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.service.UserService;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import static cn.zzwtsy.pu.tools.DataBaseStatic.DB_FILE_FULL_PATH;
import static cn.zzwtsy.pu.tools.DataBaseStatic.PLUGIN_DATA_FILE_PATH;
import static cn.zzwtsy.pu.tools.MyStatic.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.PATH_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.SETTING_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.settingBean;
import static cn.zzwtsy.pu.utils.DateUtil.complementaryDate;
import static java.lang.Math.abs;

/**
 * 拆分消息
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class Tools {
    /**
     * 获取外部字体
     *
     * @param fontSize 字体大小
     * @param fontPath 字体路径
     * @return {@link Font}
     * @throws IOException         ioexception
     * @throws URISyntaxException  URISyntaxException
     * @throws FontFormatException 字体格式异常
     */
    public static Font getFont(String fontPath, int fontSize) throws IOException, URISyntaxException, FontFormatException {
        InputStream resourceAsStream = Tools.class.getResourceAsStream(fontPath);
        assert resourceAsStream != null;
        Font font = Font.createFont(Font.TRUETYPE_FONT, resourceAsStream);
        return font.deriveFont(Font.PLAIN, fontSize);
    }

    /**
     * 检查时间
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
        return new UserService().getUser(qqId) != null;
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
        return qqId == settingBean.getAdminId();
    }

    /**
     * 检查设置文件是否存在
     *
     * @return boolean
     */
    public static boolean checkSettingFile() {
        File settingFile = new File("config/" + PATH_NAME + "/" + SETTING_FILE_NAME + ".json");
        return settingFile.exists();
    }

    /**
     * 检查命令文件是否存在
     *
     * @return boolean
     */
    public static boolean checkCommandFile() {
        File commandFile = new File("config/" + PATH_NAME + "/" + COMMAND_FILE_NAME + ".json");
        return commandFile.exists();
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
            if (new File(PLUGIN_DATA_FILE_PATH).mkdirs()) {
                PuCampus.INSTANCE.getLogger().info("创建数据库文件夹成功");
                return false;
            }
            PuCampus.INSTANCE.getLogger().error("创建数据库文件夹失败");
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String addDate = complementaryDate(settingBean.getTimedTaskTime());
        long timedTaskTime = sdf.parse(addDate).getTime();
        long nowTime = System.currentTimeMillis();
        long delayTime = (timedTaskTime - nowTime) / 1000L;
        if (delayTime > 0) {
            return delayTime;
        } else if (delayTime == 0) {
            return 0;
        } else {
            return 86400 - abs(delayTime);
        }
    }
}
