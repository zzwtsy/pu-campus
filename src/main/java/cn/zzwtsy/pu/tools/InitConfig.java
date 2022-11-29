package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.javabean.Command;
import cn.zzwtsy.pu.javabean.UserConfig;

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
    public void initUserConfig() {
        UserConfig.INSTANCE.setAdminId(0)
                .setGroupId(0)
                .setEmailSuffix("")
                .setOauthToken("")
                .setOauthTokenSecret("");
    }

    /**
     * 初始化命令配置
     */
    public void initCommandConfig() {
        Command.INSTANCE.setCommandPrefix("#")
                .setLogin("登录")
                .setGetCalendarEventList("获取活动列表")
                .setQueryNewEventList("新活动列表")
                .setQuerySignInEventList("待签到活动")
                .setQuerySignOutEventList("待签到活动退")
                .setQueryActivityDetailById("活动信息");
    }
}
