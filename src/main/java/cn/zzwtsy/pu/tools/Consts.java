package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.SettingBean;
import cn.zzwtsy.pu.bean.command.CommandBean;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author zzwtsy
 * @since 2022/11/29
 */
public class Consts {
    public static final String SETTING_FILE_NAME = "setting";
    public static final String COMMAND_FILE_NAME = "command";
    /**
     * 存放配置文件路径名
     */
    public static final String PATH_NAME = PuCampus.INSTANCE.getConfigFolder().getName();
    public static SettingBean settingBean;
    public static CommandBean commandBean;
    public static final Map<Long, ScheduledFuture<?>> TASKS_MAP = new HashMap<>();

    public static final String DRIVER_CLASS_NAME = "org.sqlite.JDBC";
    /**
     * 数据库文件名字
     */
    public static final String DB_NAME = "pu.db";
    /**
     * 插件数据文件路径
     */
    public static final String PLUGIN_DATA_FILE_PATH = "data/cn.zzwtsy.pu/";
    /**
     * 数据库文件完整路径
     */
    public static final String DB_FILE_FULL_PATH = PLUGIN_DATA_FILE_PATH + DB_NAME;
    /**
     * 数据库连接url
     */
    public static final String SQL_CONNECT_URL = "jdbc:sqlite:" + DB_FILE_FULL_PATH;

}
