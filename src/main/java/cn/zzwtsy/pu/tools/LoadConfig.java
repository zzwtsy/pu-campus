package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.javabean.Command;
import cn.zzwtsy.pu.javabean.UserConfig;
import cn.zzwtsy.pu.utils.ConfigHelper;

import java.io.IOException;

import static cn.zzwtsy.pu.tools.MyStatic.*;

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
    public void loadAllConfig() {
        PuCampus.INSTANCE.getLogger().info("Loading user config file");
        try {
            botUserConfig = ConfigHelper.getConfigFromFile(USER_CONFIG_FILE_NAME, UserConfig.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading user config file error", e);
        }
        PuCampus.INSTANCE.getLogger().info("Loading command config file");
        try {
            botCommand = ConfigHelper.getConfigFromFile(COMMAND_FILE_NAME, Command.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading command config file error", e);
        }
    }

    /**
     * 加载用户配置
     */
    public void loadUserConfig() {
        PuCampus.INSTANCE.getLogger().info("Loading user config file");
        try {
            botUserConfig = ConfigHelper.getConfigFromFile(USER_CONFIG_FILE_NAME, UserConfig.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading user config file error", e);
        }
    }

    /**
     * 加载命令配置
     */
    public void loadCommandConfig() {
        PuCampus.INSTANCE.getLogger().info("Loading command config file");
        try {
            botCommand = ConfigHelper.getConfigFromFile(COMMAND_FILE_NAME, Command.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading command config file error", e);
        }
    }
}
