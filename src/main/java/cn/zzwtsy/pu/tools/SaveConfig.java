package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.command.CommandBean;
import cn.zzwtsy.pu.utils.ConfigHelper;
import java.io.IOException;

import static cn.zzwtsy.pu.tools.Consts.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.Consts.PATH_NAME;


/**
 * 保存配置
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class SaveConfig {
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
