package cn.zzwtsy.pu.tools;

import static cn.zzwtsy.pu.tools.Consts.commandBean;

/**
 * 命令常量
 *
 * @author zzwtsy
 * @since 2023/01/29
 */
@SuppressWarnings("PMD")
public class CommandConsts {
    private static final String commandPrefix = commandBean.getPublicBean().getCommandPrefix();
    /*群聊命令*/
    public static final String eventListCommand = commandPrefix + commandBean.getGroupBean().getGetCalendarEventList();
    public static final String helpCommand = commandPrefix + commandBean.getPublicBean().getHelp();
    public static final String queryUserCreditInfoCommand = commandPrefix + commandBean.getGroupBean().getQueryUserCreditInfo();
    public static final String querySignInEventListCommand = commandPrefix + commandBean.getGroupBean().getQuerySignInEventList();
    public static final String querySignOutEventListCommand = commandPrefix + commandBean.getGroupBean().getQuerySignOutEventList();
    public static final String queryUserEventEndUnissuedCreditListCommand = commandPrefix + commandBean.getGroupBean().getQueryUserEventEndUnissuedCreditList();
    public static final String deleteUserCommand = commandPrefix + commandBean.getPrivateBean().getDeleteUser();
    /*用户私聊命令*/
    public static final String loginCommand = commandPrefix + commandBean.getPrivateBean().getLogin();
    /*管理员命令*/
    public static final String addPublicToken = commandPrefix + commandBean.getAdminBean().getAddPublicToken();
    public static final String adminDeleteUserCommand = commandPrefix + commandBean.getAdminBean().getAdminDeleteUser();
    public static final String timedTaskCommand = commandPrefix + commandBean.getAdminBean().getTimedTask();
    public static final String showTaskCommand = commandPrefix + commandBean.getAdminBean().getShowTask();
}
