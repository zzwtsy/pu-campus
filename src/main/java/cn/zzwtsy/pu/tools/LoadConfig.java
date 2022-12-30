package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.Command;
import cn.zzwtsy.pu.bean.Setting;
import cn.zzwtsy.pu.utils.ConfigHelper;

import java.io.IOException;

import static cn.zzwtsy.pu.tools.MyStatic.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.PATH_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.SETTING_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.setting;
import static cn.zzwtsy.pu.tools.MyStatic.command;

/**
 * 加载配置
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class LoadConfig {
    /**
     * 加载所有配置
     */
    public static void loadAllConfig() {
        PuCampus.INSTANCE.getLogger().info("Loading setting config file");
        try {
            setting = ConfigHelper.getConfigFromFile(PATH_NAME, SETTING_FILE_NAME, Setting.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading setting config file error", e);
        }
        PuCampus.INSTANCE.getLogger().info("Loading command config file");
        try {
            command = ConfigHelper.getConfigFromFile(PATH_NAME, COMMAND_FILE_NAME, Command.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading command config file error", e);
        }
    }

    /**
     * 加载用户配置
     */
    public static void loadUserConfig() {
        PuCampus.INSTANCE.getLogger().info("Loading user config file");
        try {
            setting = ConfigHelper.getConfigFromFile(PATH_NAME, SETTING_FILE_NAME, Setting.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading user config file error", e);
        }
    }

    /**
     * 加载命令配置
     */
    public static void loadCommandConfig() {
        PuCampus.INSTANCE.getLogger().info("Loading command config file");
        try {
            command = ConfigHelper.getConfigFromFile(PATH_NAME, COMMAND_FILE_NAME, Command.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading command config file error", e);
        }
    }
}
