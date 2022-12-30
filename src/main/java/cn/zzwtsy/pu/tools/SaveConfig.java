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
 * 保存配置
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class SaveConfig {
    /**
     * 保存所有配置
     *
     * @return boolean
     */
    public static boolean saveAllConfig() {
        try {
            ConfigHelper.setConfigFile(PATH_NAME, SETTING_FILE_NAME, setting);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save setting config file failed", e);
            return false;
        }
        try {
            ConfigHelper.setConfigFile(PATH_NAME, COMMAND_FILE_NAME, command);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save command config file failed", e);
            return false;
        }
        return true;
    }

    /**
     * 保存用户配置
     */
    public static boolean saveSettingConfig(Setting setting) {
        try {
            ConfigHelper.setConfigFile(PATH_NAME, SETTING_FILE_NAME, setting);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save setting config file failed", e);
            return false;
        }
        return true;
    }

    /**
     * 保存命令配置
     */
    public static boolean saveCommandConfig(Command command) {
        try {
            ConfigHelper.setConfigFile(PATH_NAME, COMMAND_FILE_NAME, command);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save command config file failed", e);
            return false;
        }
        return true;
    }
}
