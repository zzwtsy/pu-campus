package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.javabean.Command;
import cn.zzwtsy.pu.javabean.UserConfig;
import cn.zzwtsy.pu.utils.ConfigHelper;

import java.io.IOException;

import static cn.zzwtsy.pu.tools.MyStatic.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.USER_CONFIG_FILE_NAME;

/**
 * 初始化配置
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class InitConfig {
    /**
     * 初始化用户配置
     */
    public boolean initConfig() {
        PuCampus.INSTANCE.getLogger().info("Init User Config");
        UserConfig.INSTANCE.setAdminId(0)
                .setGroupId(0)
                .setEmailSuffix("")
                .setOauthToken("")
                .setOauthTokenSecret("");
        try {
            ConfigHelper.createConfigFile(USER_CONFIG_FILE_NAME);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Failed to create User Config file", e);
            return false;
        }
        PuCampus.INSTANCE.getLogger().info("Init Command Config");
        Command.INSTANCE.setCommandPrefix("#")
                .setLogin("登录")
                .setGetCalendarEventList("获取活动列表")
                .setQueryNewEventList("新活动列表")
                .setQuerySignInEventList("待签到活动")
                .setQuerySignOutEventList("待签到活动退")
                .setQueryActivityDetailById("活动信息");
        try {
            ConfigHelper.createConfigFile(COMMAND_FILE_NAME);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Failed to create command config file", e);
            return false;
        }
        return new SaveConfig().saveAllConfig();
    }
}
