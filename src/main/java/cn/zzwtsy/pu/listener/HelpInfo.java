package cn.zzwtsy.pu.listener;

import static cn.zzwtsy.pu.tools.MyStatic.command;

/**
 * 帮助信息
 *
 * @author zzwtsy
 * @since 2023/01/22
 */
public class HelpInfo {
    //群聊命令
    private final String eventListCommand = command.getCommandPrefix() + command.getGetCalendarEventList();
    private final String helpCommand = command.getCommandPrefix() + command.getHelp();
    private final String loginCommand = command.getCommandPrefix() + command.getLogin();
    private final String queryUserCreditInfoCommand = command.getCommandPrefix() + command.getQueryUserCreditInfo();
    private final String querySignInEventListCommand = command.getCommandPrefix() + command.getQuerySignInEventList();
    private final String querySignOutEventListCommand = command.getCommandPrefix() + command.getQuerySignOutEventList();
    private final String queryUserEventEndUnissuedCreditListCommand = command.getCommandPrefix() + command.getQueryUserEventEndUnissuedCreditList();
    //用户私聊命令
    private final String deleteUserCommand = command.getCommandPrefix() + command.getDeleteUser();
    //管理员命令
    private final String addPublicToken = command.getCommandPrefix() + command.getAddPublicToken();
    private final String adminDeleteUserCommand = command.getCommandPrefix() + command.getAdminDeleteUser();
    private final String timedTaskCommand = command.getCommandPrefix() + command.getTimedTask();


    public String groupHelpInfo() {
        return eventListCommand + "<日期格式(04-08)|今日|今天|昨日|昨天|明日|明天>" + "根据日期获取活动列表"
                + "\n" + querySignInEventListCommand + "获取需要签到的活动列表"
                + "\n" + querySignOutEventListCommand + "获取需要签退的活动列表"
                + "\n" + queryUserCreditInfoCommand + "获取学分信息"
                + "\n" + queryUserEventEndUnissuedCreditListCommand + "获取活动已结束但未发放学分的活动信息"
                + "\n" + helpCommand + "获取帮助信息";
    }

    public String privateHelpInfo() {
        return loginCommand + "<用户名|oauthToken> <密码|oauthTokenSecret>" + "登陆 pu 校园"
                + "\n" + deleteUserCommand + "删除个人信息"
                + "\n" + helpCommand + "获取帮助信息";
    }

    public String adminHelpInfo() {
        return addPublicToken + "<用户名|oauthToken> <密码|oauthTokenSecret>" + "添加公共 token"
                + "\n" + adminDeleteUserCommand + "<用户 qq 号>" + "删除用户信息"
                + "\n" + timedTaskCommand + "<时间为24小时制>" + "设置定时任务时间"
                + "\n" + helpCommand + "获取帮助信息";
    }
}
