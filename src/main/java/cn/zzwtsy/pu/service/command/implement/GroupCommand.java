package cn.zzwtsy.pu.service.command.implement;

import cn.zzwtsy.pu.service.EventListService;
import cn.zzwtsy.pu.service.UserCreditService;
import cn.zzwtsy.pu.service.command.Command;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import static cn.zzwtsy.pu.tools.MyStatic.commandBean;
import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;
import static cn.zzwtsy.pu.tools.Tools.splitMessage;
import static cn.zzwtsy.pu.utils.DateUtil.addYear;
import static cn.zzwtsy.pu.utils.DateUtil.checkDateFormat;
import static cn.zzwtsy.pu.utils.DateUtil.dateCalculate;

/**
 * 群组
 *
 * @author zzwtsy
 * @since 2023/01/26
 */
public class GroupCommand implements Command {
    private final String commandPrefix = commandBean.getPublicBean().getCommandPrefix();
    private final String eventListCommand = commandPrefix + commandBean.getGroupBean().getGetCalendarEventList();
    private final String helpCommand = commandPrefix + commandBean.getPublicBean().getHelp();
    private final String loginCommand = commandPrefix + commandBean.getPrivateBean().getLogin();
    private final String queryUserCreditInfoCommand = commandPrefix + commandBean.getGroupBean().getQueryUserCreditInfo();
    private final String querySignInEventListCommand = commandPrefix + commandBean.getGroupBean().getQuerySignInEventList();
    private final String querySignOutEventListCommand = commandPrefix + commandBean.getGroupBean().getQuerySignOutEventList();
    private final String queryUserEventEndUnissuedCreditListCommand = commandPrefix + commandBean.getGroupBean().getQueryUserEventEndUnissuedCreditList();

    @Override
    public MessageChain processingCommand(String message, long userQqId) {
        //获取帮助信息
        if (message.startsWith(helpCommand)) {
            CommandPublicMethod commandPublicMethod = new CommandPublicMethod();
            return new MessageChainBuilder()
                    .append("===群聊可使用命令===\n\n")
                    .append(commandPublicMethod.groupHelpInfo())
                    .append("\n\n\n===私聊可使用命令===\n\n")
                    .append(commandPublicMethod.privateHelpInfo())
                    .build();
        }
        if (!checkUserLogin(userQqId)) {
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus("你还没有登陆请先私聊机器人登陆PU校园账户"))
                    .build();
        }
        //根据日期获取活动列表
        if (message.startsWith(eventListCommand)) {
            int commandLength = 2;
            String[] strings = splitMessage(message);
            if (strings.length != commandLength) {
                return new MessageChainBuilder()
                        .append("命令格式错误")
                        .build();
            }
            String eventList = getEventList(strings[1], userQqId);
            return new MessageChainBuilder()
                    .append(eventList)
                    .build();
        }
        //获取活动已结束未发放学分列表
        if (message.startsWith(queryUserEventEndUnissuedCreditListCommand)) {
            String eventList = new EventListService().getUserEventEndUnissuedCreditList(userQqId);
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus("\n")
                            .plus(eventList))
                    .build();
        }
        //用户登录
        if (message.startsWith(loginCommand)) {
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus("请私聊机器人登录"))
                    .build();
        }
        //查询学分信息
        if (message.startsWith(queryUserCreditInfoCommand)) {
            if (!checkUserLogin(userQqId)) {
                return new MessageChainBuilder()
                        .append(new At(userQqId)
                                .plus("你还没有登陆，请私聊机器人登录PU校园"))
                        .build();
            }
            String userCreditInfoMessage = new UserCreditService().userCredit(userQqId);
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus(userCreditInfoMessage))
                    .build();
        }
        //获取待签到列表
        if (message.startsWith(querySignInEventListCommand)) {
            String eventList = new EventListService().getUserCanSignInEventList(userQqId);
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus("\n\n")
                            .plus(eventList))
                    .build();
        }
        //获取待签退列表
        if (message.startsWith(querySignOutEventListCommand)) {
            String eventList = new EventListService().getUserCanSignOutEventList(userQqId);
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus("\n\n")
                            .plus(eventList))
                    .build();
        }
        return null;
    }

    /**
     * 获取活动列表
     *
     * @param dateParameter 日期
     * @param userQqId      用户qq号
     */
    private String getEventList(String dateParameter, long userQqId) {
        String date;
        switch (dateParameter) {
            case "今日":
            case "今天":
                date = dateCalculate(0);
                break;
            case "明日":
            case "明天":
                date = dateCalculate(+1);
                break;
            case "昨日":
            case "昨天":
                date = dateCalculate(-1);
                break;
            default:
                date = addYear(dateParameter);
                break;
        }
        if (!checkDateFormat(date)) {
            return "日期格式错误";
        }
        if (!checkUserLogin(userQqId)) {
            return "你还没有登陆请先私聊机器人登陆PU校园账户";
        }
        return new EventListService().getCalendarEventList(userQqId, date);
    }
}
