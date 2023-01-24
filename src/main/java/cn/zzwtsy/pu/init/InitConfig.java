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
        Setting.INSTANCE.setAdminId(0)
                .setGroupId(0)
                .setBotId(0)
                .setTimedTaskTime("0")
                .setEmailSuffix("");
        try {
            if (ConfigHelper.createConfigFile(PATH_NAME, SETTING_FILE_NAME)) {
                PuCampus.INSTANCE.getLogger().info("Create setting config file successfully");
            } else {
                PuCampus.INSTANCE.getLogger().error("Create setting config file failed");
            }
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Create setting config file failed", e);
            return false;
        }
        return SaveConfig.saveSettingConfig(Setting.INSTANCE);
    }

    public boolean initCommandConfig() {
        Command.INSTANCE.setPublicX(new Command.PublicBean()
                .setHelp("help")
                .setCommandPrefix("#"));
        Command.INSTANCE.setGroup(new Command.GroupBean()
                .setGetCalendarEventList("活动")
                .setQueryActivityDetailById("活动信息")
                .setQueryUserCreditInfo("学分信息")
                .setQuerySignInEventList("签到")
                .setQuerySignOutEventList("签退")
                .setQueryUserEventEndUnissuedCreditList("未发放学分活动")
        );
        Command.INSTANCE.setPrivateX(new Command.PrivateBean().setLogin("登陆").setDeleteUser("删除信息"));
        Command.INSTANCE.setAdmin(new Command.AdminBean()
                .setAdminDeleteUser("删除用户")
                .setTimedTask("定时任务")
                .setAddPublicToken("添加tk")
        );
        try {
            if (ConfigHelper.createConfigFile(PATH_NAME, COMMAND_FILE_NAME)) {
                PuCampus.INSTANCE.getLogger().info("Create command config file successfully");
            } else {
                PuCampus.INSTANCE.getLogger().error("Create command config file failed" + getClass().getSimpleName());
            }
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("Create command config file failed", e);
            return false;
        }
        return SaveConfig.saveCommandConfig(Command.INSTANCE);
    }
}
