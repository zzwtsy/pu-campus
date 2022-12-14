package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.service.EventListService;
import cn.zzwtsy.pu.service.UserCreditService;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;

import static cn.zzwtsy.pu.tools.MyStatic.command;
import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;
import static cn.zzwtsy.pu.tools.Tools.splitMessage;
import static cn.zzwtsy.pu.utils.DateUtil.addYear;
import static cn.zzwtsy.pu.utils.DateUtil.checkDateFormat;
import static cn.zzwtsy.pu.utils.DateUtil.dateCalculate;

/**
 * 监听群消息
 *
 * @author zzwtsy
 * @since 2022/12/01
 */
public class ListenerGroupMessage extends SimpleListenerHost {
    private final String eventListCommand = command.getCommandPrefix() + command.getGetCalendarEventList();
    private final String helpCommand = command.getCommandPrefix() + command.getHelp();
    private final String loginCommand = command.getCommandPrefix() + command.getLogin();
    private final String queryUserCreditInfoCommand = command.getCommandPrefix() + command.getQueryUserCreditInfo();
    private final String queryNewEventListCommand = command.getCommandPrefix() + command.getQueryNewEventList();
    private final String querySignInEventListCommand = command.getCommandPrefix() + command.getQuerySignInEventList();
    private final String querySignOutEventListCommand = command.getCommandPrefix() + command.getQuerySignOutEventList();
    String message;
    GroupMessageEvent groupMessageEvent;

    @EventHandler
    private void onEvent(GroupMessageEvent event) {
        this.groupMessageEvent = event;
        run(groupMessageEvent);
    }

    private void run(GroupMessageEvent groupMessageEvent) {
        message = groupMessageEvent.getMessage().contentToString();
        long userQqId = groupMessageEvent.getSender().getId();
        //获取待签到列表
        if (message.startsWith(querySignInEventListCommand)) {
            String message = new EventListService().getUserCanSignInEventList(userQqId);
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("\n").plus(message));
            return;
        }
        //获取待签退列表
        if (message.startsWith(querySignOutEventListCommand)) {
            String message = new EventListService().getUserCanSignOutEventList(userQqId);
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("\n").plus(message));
            return;
        }
        //获取新活动列表
        if (message.startsWith(queryNewEventListCommand)) {
            if (!checkUserLogin(userQqId)) {
                groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("你还没有登陆请先私聊机器人登陆PU校园账户"));
                return;
            }
            String newEventList = new EventListService().getNewEventList(userQqId);
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("\n").plus(newEventList));
            return;
        }
        //根据日期获取活动列表
        if (message.startsWith(eventListCommand)) {
            String[] strings = splitMessage(message);
            getEventList(strings[1], userQqId);
            return;
        }
        //获取帮助信息
        if (message.startsWith(helpCommand)) {
            helpInfo();
            return;
        }
        //用户登录
        if (message.startsWith(loginCommand)) {
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("请私聊机器人登录"));
            return;
        }
        //查询学分信息
        if (message.startsWith(queryUserCreditInfoCommand)) {
            if (!checkUserLogin(userQqId)) {
                groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("你还没有登陆，请私聊机器人登录PU校园"));
                return;
            }
            String userCreditInfoMessage = new UserCreditService().userCredit(userQqId);
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus(userCreditInfoMessage));
        }
    }

    /**
     * 获取活动列表
     *
     * @param dateParameter 日期
     * @param userQqId      用户qq号
     */
    private void getEventList(String dateParameter, long userQqId) {
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
                if (!checkDateFormat(dateParameter)) {
                    groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("日期格式错误"));
                    return;
                }
                date = addYear(dateParameter);
                break;
        }
        if (!checkUserLogin(userQqId)) {
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("你还没有登陆请先私聊机器人登陆PU校园账户"));
            return;
        }
        groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("正在获取").plus(date).plus("活动列表"));
        String eventList = new EventListService().getCalendarEventList(userQqId, date);
        groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("\n\n").plus(eventList));
    }

    /**
     * 帮助信息
     */
    private void helpInfo() {
        String helpMessage = "用户命令：\n"
                + command.getCommandPrefix() + command.getDeleteUser() + "：删除自己的用户信息\n"
                + command.getCommandPrefix() + command.getGetCalendarEventList()
                + "\n <日期(12-12)|今日|今天|明日|明天|昨日|昨天>：获取活动列表\n"
                + command.getCommandPrefix() + command.getLogin() + " <用户名> <用户密码>：登录PU校园\n"
                + command.getCommandPrefix() + command.getQuerySignInEventList() + "：查询待签到活动列表\n"
                + command.getCommandPrefix() + command.getQuerySignOutEventList() + "：查询待签退活动列表\n"
                + command.getCommandPrefix() + command.getQueryActivityDetailById() + "：查询活动详细信息\n"
                + command.getCommandPrefix() + command.getQueryNewEventList() + "获取新活动列表\n"
                + command.getCommandPrefix() + command.getQueryUserCreditInfo() + "获取个人学分信息\n"
                + command.getCommandPrefix() + command.getHelp() + "：获取帮助信息\n";
        groupMessageEvent.getGroup().sendMessage(helpMessage);
    }
}
