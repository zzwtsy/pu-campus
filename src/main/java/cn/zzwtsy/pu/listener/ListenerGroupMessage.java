package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.bean.User;
import cn.zzwtsy.pu.service.EventListService;
import cn.zzwtsy.pu.service.NewEventListService;
import cn.zzwtsy.pu.service.UserCreditService;
import cn.zzwtsy.pu.service.UserService;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;

import static cn.zzwtsy.pu.tools.CheckUser.checkUserLogin;
import static cn.zzwtsy.pu.tools.MyStatic.command;
import static cn.zzwtsy.pu.tools.SplitMessage.splitMessage;
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
        if (message.startsWith(queryNewEventListCommand)) {
            if (!checkUserLogin(String.valueOf(userQqId))) {
                groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("你还没有登陆请先私聊机器人登陆PU校园账户"));
                return;
            }
            String newEventList = "\n" + new NewEventListService().getNewEventList(String.valueOf(userQqId));
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus(newEventList));
            return;
        }
        if (message.startsWith(eventListCommand)) {
            String[] strings = splitMessage(message);
            switch (strings[1]) {
                case "今日":
                case "今天":
                    getEventList(dateCalculate(0), false);
                    break;
                case "明日":
                case "明天":
                    getEventList(dateCalculate(+1), false);
                    break;
                case "昨日":
                case "昨天":
                    getEventList(dateCalculate(-1), false);
                    break;
                default:
                    if (!checkDateFormat(strings[1])) {
                        groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("日期格式错误"));
                        break;
                    }
                    getEventList(addYear(strings[1]), true);
                    break;
            }
            return;
        }
        if (message.startsWith(helpCommand)) {
            helpInfo();
            return;
        }
        if (message.startsWith(loginCommand)) {
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("请私聊机器人登录"));
            return;
        }
        if (message.startsWith(queryUserCreditInfoCommand)) {
            if (!checkUserLogin(String.valueOf(userQqId))) {
                groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("你还没有登陆，请私聊机器人登录PU校园"));
                return;
            }
            User user = new UserService().getUser(String.valueOf(userQqId));
            String userCreditInfoMessage = new UserCreditService().userCredit(user.getOauthToken(), user.getOauthTokenSecret());
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus(userCreditInfoMessage));
        }
    }

    /**
     * 获取事件列表
     *
     * @param date            日期
     * @param checkDateFormat 是否检查日期格式
     */
    private void getEventList(String date, boolean checkDateFormat) {
        long userQqId = groupMessageEvent.getSender().getId();
        if (!checkUserLogin(String.valueOf(userQqId))) {
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("你还没有登陆请先私聊机器人登陆PU校园账户"));
            return;
        }
        if (checkDateFormat && !checkDateFormat(date)) {
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("日期格式错误"));
        } else {
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("正在获取" + date + "活动列表"));
            String eventList = new EventListService().getCalendarEventList(String.valueOf(userQqId), date);
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("\n\n" + eventList));
        }
    }

    /**
     * 帮助信息
     */
    private void helpInfo() {
        String helpMessage = "用户命令：\n"
                + command.getCommandPrefix() + command.getDeleteUser() + "：删除自己的用户信息\n"
                + command.getCommandPrefix() + command.getGetCalendarEventList() + " <日期(12-12)|今日|今天|明日|明天|昨日|昨天>：获取活动列表\n"
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
