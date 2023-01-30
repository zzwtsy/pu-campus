package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.SettingBean;
import cn.zzwtsy.pu.bean.command.CommandBean;
import cn.zzwtsy.pu.utils.ConfigHelper;
import java.io.IOException;

import static cn.zzwtsy.pu.tools.Consts.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.Consts.PATH_NAME;
import static cn.zzwtsy.pu.tools.Consts.SETTING_FILE_NAME;
import static cn.zzwtsy.pu.tools.Consts.commandBean;
import static cn.zzwtsy.pu.tools.Consts.settingBean;


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
            ConfigHelper.setConfigFile(PATH_NAME, SETTING_FILE_NAME, settingBean);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save setting config file failed", e);
            return false;
        }
        try {
            ConfigHelper.setConfigFile(PATH_NAME, COMMAND_FILE_NAME, commandBean);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save command config file failed", e);
            return false;
        }
        return true;
    }

    /**
     * 保存用户配置
     */
    public static boolean saveSettingConfig(SettingBean settingBean) {
        try {
            ConfigHelper.setConfigFile(PATH_NAME, SETTING_FILE_NAME, settingBean);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save setting config file failed", e);
            return false;
        }
        return true;
    }

    /**
     * 保存命令配置
     */
    public static boolean saveCommandConfig(CommandBean commandBean) {
        try {
            ConfigHelper.setConfigFile(PATH_NAME, COMMAND_FILE_NAME, commandBean);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save command config file failed", e);
            return false;
        }
        return true;
    }
}
