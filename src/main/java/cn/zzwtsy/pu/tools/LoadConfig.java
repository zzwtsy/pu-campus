package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.command.CommandBean;
import cn.zzwtsy.pu.utils.ConfigHelper;
import java.io.IOException;

import static cn.zzwtsy.pu.tools.Consts.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.Consts.PATH_NAME;
import static cn.zzwtsy.pu.tools.Consts.commandBean;

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
        try {
            commandBean = ConfigHelper.getConfigFromFile(PATH_NAME, COMMAND_FILE_NAME, CommandBean.class);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Loading command config file error", e);
        }
    }
}
