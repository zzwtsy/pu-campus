package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.service.EventListService;
import cn.zzwtsy.pu.service.UserService;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static cn.zzwtsy.pu.tools.MyStatic.command;
import static cn.zzwtsy.pu.tools.SplitMessage.splitMessage;

/**
 * 监听群消息
 *
 * @author zzwtsy
 * @since 2022/12/01
 */
public class ListenerGroupMessage extends SimpleListenerHost {
    private final String EVENT_LIST = command.getCommandPrefix() + command.getGetCalendarEventList();
    String message;
    GroupMessageEvent event;
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
            5,
            10,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(2),
            new ThreadPoolExecutor.DiscardPolicy());

    @EventHandler
    private void onEvent(GroupMessageEvent event) {
        this.event = event;
        message = event.getMessage().contentToString();
        executor.execute(() -> {
            if (message.startsWith(EVENT_LIST)) {
                String[] strings = splitMessage(message);
                switch (strings[1]) {
                    case "今日":
                    case "今天":
                        getEventList(dateCalculate(0), event, false);
                        break;
                    case "明日":
                    case "明天":
                        getEventList(dateCalculate(+1), event, false);
                        break;
                    case "昨日":
                    case "昨天":
                        getEventList(dateCalculate(-1), event, false);
                        break;
                    default:
                        getEventList(addYear(strings[1]), event, true);
                        break;
                }
            }
        });
    }

    private void getEventList(String date, GroupMessageEvent groupMessageEvent, boolean isCheckDateFormat) {
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

    /**
     * 检查日期格式
     *
     * @param date 日期
     * @return boolean
     */
    private boolean checkDateFormat(String date) {
        String dateFormat = "^\\d{1,2}-\\d{1,2}";
        return Pattern.matches(dateFormat, date);
    }

    /**
     * 日期计算
     *
     * @param amount 加减日期天数
     * @return {@link String}
     */
    private String dateCalculate(int amount) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, amount);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 添加年份
     *
     * @param date 日期
     * @return {@link String}
     */
    private String addYear(String date) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) + "-" + date;
    }
}
