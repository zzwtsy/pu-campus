package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.javabean.Command;
import cn.zzwtsy.pu.javabean.UserConfig;
import cn.zzwtsy.pu.utils.ConfigHelper;

import java.io.IOException;

/**
 * 保存配置
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class SaveConfig {

    /**
     * 保存用户配置
     *
     * @throws IOException ioexception
     */
    public void saveUserConfig() throws IOException {
        ConfigHelper.setConfigFile("userConfig", UserConfig.INSTANCE);
    }

    /**
     * 保存命令配置
     *
     * @throws IOException ioexception
     */
    public void saveCommandConfig() throws IOException {
        ConfigHelper.setConfigFile("commandConfig", Command.INSTANCE);
    }
}
