package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.service.UserService;

import java.io.File;
import java.util.regex.Pattern;

import static cn.zzwtsy.pu.tools.DataBaseStatic.DB_FILE_FULL_PATH;
import static cn.zzwtsy.pu.tools.DataBaseStatic.DB_FILE_PATH;
import static cn.zzwtsy.pu.tools.MyStatic.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.PATH_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.SETTING_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.setting;

/**
 * 拆分消息
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class Tools {
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
        return qqId == setting.getAdminId();
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
            return new File(DB_FILE_PATH).mkdirs();
        }
    }
}
