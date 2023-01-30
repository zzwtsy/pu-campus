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
            settingBean = ConfigHelper.getConfigFromFile(PATH_NAME, SETTING_FILE_NAME, SettingBean.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading setting config file error", e);
        }
        PuCampus.INSTANCE.getLogger().info("Loading command config file");
        try {
            commandBean = ConfigHelper.getConfigFromFile(PATH_NAME, COMMAND_FILE_NAME, CommandBean.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading command config file error", e);
        }
    }

    /**
     * 加载设置
     */
    public static void loadSettingConfig() {
        PuCampus.INSTANCE.getLogger().info("Loading user config file");
        try {
            settingBean = ConfigHelper.getConfigFromFile(PATH_NAME, SETTING_FILE_NAME, SettingBean.class);
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
            commandBean = ConfigHelper.getConfigFromFile(PATH_NAME, COMMAND_FILE_NAME, CommandBean.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading command config file error", e);
        }
    }
}
