package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.Command;
import cn.zzwtsy.pu.bean.UserConfig;
import cn.zzwtsy.pu.utils.ConfigHelper;

import java.io.IOException;

import static cn.zzwtsy.pu.tools.MyStatic.*;

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
            if (ConfigHelper.createConfigFile(PATH_NAME, USER_CONFIG_FILE_NAME)) {
                PuCampus.INSTANCE.getLogger().info("Create user config successfully");
            } else {
                PuCampus.INSTANCE.getLogger().error("Create user config failed");
            }
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Create user config failed", e);
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
            if (ConfigHelper.createConfigFile(PATH_NAME, COMMAND_FILE_NAME)) {
                PuCampus.INSTANCE.getLogger().info("Create command config successfully");
            } else {
                PuCampus.INSTANCE.getLogger().error("Create command config failed" + getClass().getSimpleName());
            }
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Create command config failed", e);
            return false;
        }
        return new SaveConfig().saveAllConfig();
    }
}
