package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.data.Command;

/**
 * 命令常量
 *
 * @author zzwtsy
 * @since 2023/01/29
 */
@SuppressWarnings("PMD")
public class CommandConsts {
    /*群聊命令*/
    public static final String EVENT_LIST_COMMAND;
    public static final String HELP_COMMAND;
    public static final String QUERY_USER_CREDIT_INFO_COMMAND;
    public static final String QUERY_SIGN_IN_EVENT_LIST_COMMAND;
    public static final String QUERY_SIGN_OUT_EVENT_LIST_COMMAND;
    public static final String QUERY_USER_EVENT_END_UNISSUED_CREDIT_LIST_COMMAND;
    /*用户私聊命令*/
    public static final String DELETE_USER_COMMAND;
    public static final String LOGIN_COMMAND;
    /*管理员命令*/
    public static final String ADD_PUBLIC_TOKEN;
    public static final String ADMIN_DELETE_USER_COMMAND;
    public static final String TIMED_TASK_COMMAND;
    public static final String SHOW_TASK_COMMAND;

    static {
        String commandPrefix = Command.INSTANCE.getPublicX().getCommandPrefix();
        EVENT_LIST_COMMAND = commandPrefix + Command.INSTANCE.getGroup().getGetCalendarEventList();
        HELP_COMMAND = commandPrefix + Command.INSTANCE.getPublicX().getHelp();
        QUERY_USER_CREDIT_INFO_COMMAND = commandPrefix + Command.INSTANCE.getGroup().getQueryUserCreditInfo();
        QUERY_SIGN_IN_EVENT_LIST_COMMAND = commandPrefix + Command.INSTANCE.getGroup().getQuerySignInEventList();
        QUERY_SIGN_OUT_EVENT_LIST_COMMAND = commandPrefix + Command.INSTANCE.getGroup().getQuerySignOutEventList();
        QUERY_USER_EVENT_END_UNISSUED_CREDIT_LIST_COMMAND = commandPrefix + Command.INSTANCE.getGroup().getQueryUserEventEndUnissuedCreditList();
        DELETE_USER_COMMAND = commandPrefix + Command.INSTANCE.getPrivateX().getDeleteUser();
        LOGIN_COMMAND = commandPrefix + Command.INSTANCE.getPrivateX().getLogin();
        ADD_PUBLIC_TOKEN = commandPrefix + Command.INSTANCE.getAdmin().getAddPublicToken();
        ADMIN_DELETE_USER_COMMAND = commandPrefix + Command.INSTANCE.getAdmin().getAdminDeleteUser();
        TIMED_TASK_COMMAND = commandPrefix + Command.INSTANCE.getAdmin().getTimedTask();
        SHOW_TASK_COMMAND = commandPrefix + Command.INSTANCE.getAdmin().getShowTask();
    }

}
