package cn.zzwtsy.pu.listener;

import static cn.zzwtsy.pu.tools.MyStatic.commandBean;

/**
 * 帮助信息
 *
 * @author zzwtsy
 * @since 2023/01/22
 */
public class HelpInfo {
    private final String commandPrefix = commandBean.getPublicX().getCommandPrefix();
    //群聊命令
    private final String eventListCommand = commandPrefix + commandBean.getGroup().getGetCalendarEventList();
    private final String helpCommand = commandPrefix + commandBean.getPublicX().getHelp();
    private final String queryUserCreditInfoCommand = commandPrefix + commandBean.getGroup().getQueryUserCreditInfo();
    private final String querySignInEventListCommand = commandPrefix + commandBean.getGroup().getQuerySignInEventList();
    private final String querySignOutEventListCommand = commandPrefix + commandBean.getGroup().getQuerySignOutEventList();
    private final String queryUserEventEndUnissuedCreditListCommand = commandPrefix + commandBean.getGroup().getQueryUserEventEndUnissuedCreditList();
    private final String deleteUserCommand = commandPrefix + commandBean.getPrivateX().getDeleteUser();
    //用户私聊命令
    private final String loginCommand = commandPrefix + commandBean.getPrivateX().getLogin();
    //管理员命令
    private final String addPublicToken = commandPrefix + commandBean.getAdmin().getAddPublicToken();
    private final String adminDeleteUserCommand = commandPrefix + commandBean.getAdmin().getAdminDeleteUser();
    private final String timedTaskCommand = commandPrefix + commandBean.getAdmin().getTimedTask();


    public String groupHelpInfo() {
        return eventListCommand + " <日期格式(04-08)|今日|今天|昨日|昨天|明日|明天>" + "根据日期获取活动列表"
                + "\n" + querySignInEventListCommand + "：获取需要签到的活动列表"
                + "\n" + querySignOutEventListCommand + "：获取需要签退的活动列表"
                + "\n" + queryUserCreditInfoCommand + "：获取学分信息"
                + "\n" + queryUserEventEndUnissuedCreditListCommand + "：获取活动已结束但未发放学分的活动信息"
                + "\n" + helpCommand + "：获取帮助信息";
    }

    public String privateHelpInfo() {
        return loginCommand + " <用户名|oauthToken> <密码|oauthTokenSecret>" + "：登陆 pu 校园"
                + "\n" + deleteUserCommand + "：删除个人信息"
                + "\n" + helpCommand + "：获取帮助信息";
    }

    public String adminHelpInfo() {
        return addPublicToken + " <用户名|oauthToken> <密码|oauthTokenSecret>" + "：添加公共 token"
                + "\n" + adminDeleteUserCommand + " <用户 qq 号>" + "：删除用户信息"
                + "\n" + timedTaskCommand + " <时间为24小时制>" + "：设置定时任务时间"
                + "\n" + helpCommand + "：获取帮助信息";
    }
}
