package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.service.EventListService;
import cn.zzwtsy.pu.service.UserService;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static cn.zzwtsy.pu.tools.MyStatic.command;
import static cn.zzwtsy.pu.tools.SplitMessage.splitMessage;
import static cn.zzwtsy.pu.utils.DateUtil.*;

/**
 * 监听群消息
 *
 * @author zzwtsy
 * @since 2022/12/01
 */
public class ListenerGroupMessage extends SimpleListenerHost {
    private final String EVENT_LIST = command.getCommandPrefix() + command.getGetCalendarEventList();
    String message;
    GroupMessageEvent groupMessageEvent;
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
            5,
            10,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(2),
            new ThreadPoolExecutor.DiscardPolicy());

    @EventHandler
    private void onEvent(GroupMessageEvent event) {
        this.groupMessageEvent = event;
        message = groupMessageEvent.getMessage().contentToString();
        run();
    }

    private void run() {
        executor.execute(() -> {
            if (message.startsWith(EVENT_LIST)) {
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
                        getEventList(addYear(strings[1]), true);
                        break;
                }
            }
        });
    }

    private void getEventList(String date, boolean isCheckDateFormat) {
        long userQqId = groupMessageEvent.getSender().getId();
        if (!checkUserLogin(String.valueOf(userQqId))) {
            groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("你还没有登陆请先私聊机器人登陆PU校园账户"));
        } else {
            PuCampus.INSTANCE.getLogger().error(date);
            if (isCheckDateFormat && !checkDateFormat(date)) {
                groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("日期格式错误"));
            } else {
                groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("正在获取" + date + "活动列表"));
                String eventList = new EventListService().getCalendarEventList(String.valueOf(userQqId), date);
                groupMessageEvent.getGroup().sendMessage(new At(userQqId).plus("\n\n" + eventList));
            }
        }
    }

    /**
     * 检查用户是否登录
     *
     * @param qqId 用户qq号
     * @return boolean
     */
    private boolean checkUserLogin(String qqId) {
        return new UserService().getUser(qqId) != null;
    }
}
