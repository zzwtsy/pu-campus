package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.service.EventListService;
import cn.zzwtsy.pu.service.UserService;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;

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

    @EventHandler
    private void onEvent(GroupMessageEvent event) {
        this.event = event;
        message = event.getMessage().contentToString();

        if (message.startsWith(EVENT_LIST)) {
            long userQqId = event.getSender().getId();
            if (!checkUserPermission(String.valueOf(userQqId))) {
                event.getGroup().sendMessage(new At(userQqId).plus("你还没有登陆请先私聊机器人登陆PU校园账户"));
            } else {
                String[] strings = splitMessage(message);
                String eventList = new EventListService().getCalendarEventList(String.valueOf(userQqId), strings[1]);
                event.getGroup().sendMessage(new At(userQqId).plus(eventList));
            }
        }
    }


    /**
     * 检查用户权限
     *
     * @param qqId 用户qq号
     * @return boolean
     */
    private boolean checkUserPermission(String qqId) {
        return new UserService().getUser(qqId) == null;
    }
}
