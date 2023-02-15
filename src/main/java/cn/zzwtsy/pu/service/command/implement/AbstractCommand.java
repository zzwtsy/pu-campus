package cn.zzwtsy.pu.service.command.implement;

import cn.zzwtsy.pu.data.Setting;
import cn.zzwtsy.pu.service.LoginService;
import cn.zzwtsy.pu.service.command.Command;

import static cn.zzwtsy.pu.tools.CommandConsts.ADD_PUBLIC_TOKEN;
import static cn.zzwtsy.pu.tools.CommandConsts.ADMIN_DELETE_USER_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.DELETE_USER_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.EVENT_LIST_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.HELP_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.LOGIN_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.QUERY_SIGN_IN_EVENT_LIST_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.QUERY_SIGN_OUT_EVENT_LIST_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.QUERY_USER_CREDIT_INFO_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.QUERY_USER_EVENT_END_UNISSUED_CREDIT_LIST_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.SHOW_TASK_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.TIMED_TASK_COMMAND;
import static cn.zzwtsy.pu.tools.Tools.splitMessage;

/**
 * command 抽象类
 *
 * @author zzwtsy
 * @since 2023/01/27
 */
@SuppressWarnings("PMD")
public abstract class AbstractCommand implements Command {

    protected String groupHelpInfo() {
        return EVENT_LIST_COMMAND + " <日期格式(04-08)|今日|今天|昨日|昨天|明日|明天>" + "根据日期获取活动列表"
                + "\n" + QUERY_SIGN_IN_EVENT_LIST_COMMAND + "：获取需要签到的活动列表"
                + "\n" + QUERY_SIGN_OUT_EVENT_LIST_COMMAND + "：获取需要签退的活动列表"
                + "\n" + QUERY_USER_CREDIT_INFO_COMMAND + "：获取学分信息"
                + "\n" + QUERY_USER_EVENT_END_UNISSUED_CREDIT_LIST_COMMAND + "：获取活动已结束但未发放学分的活动信息"
                + "\n" + HELP_COMMAND + "：获取帮助信息";
    }

    protected String privateHelpInfo() {
        return LOGIN_COMMAND + " <用户名|oauthToken> <密码|oauthTokenSecret>" + "：登陆 pu 校园"
                + "\n" + DELETE_USER_COMMAND + "：删除个人信息"
                + "\n" + HELP_COMMAND + "：获取帮助信息";
    }

    protected String adminHelpInfo() {
        return ADD_PUBLIC_TOKEN + " <用户名|oauthToken> <密码|oauthTokenSecret>" + "：添加公共 token"
                + "\n" + ADMIN_DELETE_USER_COMMAND + " <用户 qq 号>" + "：删除用户信息"
                + "\n" + SHOW_TASK_COMMAND + "：显示定时任务状态"
                + "\n" + TIMED_TASK_COMMAND + " <时间为24小时制|关闭>" + "：设置定时任务时间或关闭定时任务"
                + "\n" + HELP_COMMAND + "：获取帮助信息";
    }

    /**
     * 登录
     *
     * @param message 消息
     */
    protected String login(String message, long userQqId) {
        int commandLength = 3;
        String[] strings = splitMessage(message);
        String setUserTokenStatus;
        if (strings.length != commandLength) {
            return "命令格式错误";
        }
        int messageLength = strings[1].length();
        int oauthTokenLength = 32;
        if (messageLength == oauthTokenLength) {
            setUserTokenStatus = new LoginService().getUserUid(userQqId, strings[1], strings[2]);
        } else {
            //补全用户账号: 用户账号加用户学校邮件后缀
            String userName = strings[1] + Setting.INSTANCE.getEmailSuffix();
            setUserTokenStatus = new LoginService().getUserToken(userQqId, userName, strings[2]);
        }
        return setUserTokenStatus;
    }
}
