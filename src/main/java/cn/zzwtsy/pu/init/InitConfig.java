package cn.zzwtsy.pu.init;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.Command;
import cn.zzwtsy.pu.bean.Setting;
import cn.zzwtsy.pu.tools.SaveConfig;
import cn.zzwtsy.pu.utils.ConfigHelper;

import java.io.IOException;

import static cn.zzwtsy.pu.tools.MyStatic.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.PATH_NAME;
import static cn.zzwtsy.pu.tools.MyStatic.SETTING_FILE_NAME;


/**
 * 初始化配置
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class InitConfig {
    /**
     * 初始化设置配置
     *
     * @return boolean
     */
    public boolean initSettingConfig() {
        PuCampus.INSTANCE.getLogger().info("Init Setting Config");
        Setting.INSTANCE.setAdminId(0)
                .setGroupId(0)
                .setEmailSuffix("");
        try {
            if (ConfigHelper.createConfigFile(PATH_NAME, SETTING_FILE_NAME)) {
                PuCampus.INSTANCE.getLogger().info("Create setting config successfully");
            } else {
                PuCampus.INSTANCE.getLogger().error("Create setting config failed");
            }
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Create setting config failed", e);
            return false;
        }
        return SaveConfig.saveSettingConfig(Setting.INSTANCE);
    }

    public boolean initCommandConfig() {
        PuCampus.INSTANCE.getLogger().info("Init Command Config");
        Command.INSTANCE.setCommandPrefix("#")
                .setLogin("登录")
                .setHelp("帮助信息")
                .setDeleteUser("删除我的信息")
                .setAdminDeleteUser("删除用户")
                .setQueryNewEventList("新活动列表")
                .setQueryUserCreditInfo("学分信息")
                .setQuerySignInEventList("待签到活动")
                .setQuerySignOutEventList("待签退活动")
                .setQueryActivityDetailById("活动信息")
                .setGetCalendarEventList("获取活动列表")
                .setQueryUserEventEndUnissuedCreditList("未发放学分活动");
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
        return SaveConfig.saveCommandConfig(Command.INSTANCE);
    }
}
