package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.Command;
import cn.zzwtsy.pu.bean.UserConfig;
import cn.zzwtsy.pu.utils.ConfigHelper;

import java.io.IOException;

import static cn.zzwtsy.pu.tools.MyStatic.*;

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
    public boolean saveAllConfig() {
        try {
            ConfigHelper.setConfigFile(PATH_NAME, USER_CONFIG_FILE_NAME, UserConfig.INSTANCE);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save user config file failed", e);
            return false;
        }
        try {
            ConfigHelper.setConfigFile(PATH_NAME, COMMAND_FILE_NAME, Command.INSTANCE);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Save command config file failed", e);
            return false;
        }
        return true;
    }

    /**
     * 保存用户配置
     *
     * @throws IOException ioexception
     */
    public void saveUserConfig() throws IOException {
        ConfigHelper.setConfigFile(PATH_NAME, "userConfig", UserConfig.INSTANCE);
    }

    /**
     * 保存命令配置
     *
     * @throws IOException ioexception
     */
    public void saveCommandConfig() throws IOException {
        ConfigHelper.setConfigFile(PATH_NAME, "commandConfig", Command.INSTANCE);
    }
}
