package cn.zzwtsy.pu.init;

import static cn.zzwtsy.pu.tools.Consts.commandBean;

/**
 * 加载命令
 *
 * @author zzwtsy
 * @since 2023/01/28
 */
@SuppressWarnings("PMD")
public class LoadCommands {
    private final String commandPrefix = commandBean.getPublicBean().getCommandPrefix();
    /*群聊命令*/
    public final String eventListCommand = commandPrefix + commandBean.getGroupBean().getGetCalendarEventList();
    public final String helpCommand = commandPrefix + commandBean.getPublicBean().getHelp();
    public final String queryUserCreditInfoCommand = commandPrefix + commandBean.getGroupBean().getQueryUserCreditInfo();
    public final String querySignInEventListCommand = commandPrefix + commandBean.getGroupBean().getQuerySignInEventList();
    public final String querySignOutEventListCommand = commandPrefix + commandBean.getGroupBean().getQuerySignOutEventList();
    public final String queryUserEventEndUnissuedCreditListCommand = commandPrefix + commandBean.getGroupBean().getQueryUserEventEndUnissuedCreditList();
    public final String deleteUserCommand = commandPrefix + commandBean.getPrivateBean().getDeleteUser();
    /*用户私聊命令*/
    public final String loginCommand = commandPrefix + commandBean.getPrivateBean().getLogin();
    /*管理员命令*/
    public final String addPublicToken = commandPrefix + commandBean.getAdminBean().getAddPublicToken();
    public final String adminDeleteUserCommand = commandPrefix + commandBean.getAdminBean().getAdminDeleteUser();
    public final String timedTaskCommand = commandPrefix + commandBean.getAdminBean().getTimedTask();
}
