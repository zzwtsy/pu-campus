package cn.zzwtsy.pu.init;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.command.AdminBean;
import cn.zzwtsy.pu.bean.command.CommandBean;
import cn.zzwtsy.pu.bean.command.GroupBean;
import cn.zzwtsy.pu.bean.command.PrivateBean;
import cn.zzwtsy.pu.bean.command.PublicBean;
import cn.zzwtsy.pu.tools.SaveConfig;
import cn.zzwtsy.pu.utils.ConfigHelper;
import java.io.IOException;

import static cn.zzwtsy.pu.tools.Consts.COMMAND_FILE_NAME;
import static cn.zzwtsy.pu.tools.Consts.PATH_NAME;


/**
 * 初始化配置
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class InitConfig {

    public boolean initCommandConfig() {
        CommandBean.INSTANCE.setPublicBean(new PublicBean()
                .setHelp("help")
                .setCommandPrefix("#"));
        CommandBean.INSTANCE.setGroupBean(new GroupBean()
                .setGetCalendarEventList("活动")
                .setQueryActivityDetailById("活动信息")
                .setQueryUserCreditInfo("学分")
                .setQuerySignInEventList("签到")
                .setQuerySignOutEventList("签退")
                .setQueryUserEventEndUnissuedCreditList("未发放")
        );
        CommandBean.INSTANCE.setPrivateBean(new PrivateBean()
                .setLogin("登录")
                .setDeleteUser("删除信息"));
        CommandBean.INSTANCE.setAdminBean(new AdminBean()
                .setAdminDeleteUser("删除用户")
                .setTimedTask("定时任务")
                .setAddPublicToken("添加tk")
                .setShowTask("显示任务")
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
        return SaveConfig.saveCommandConfig(CommandBean.INSTANCE);
    }
}
