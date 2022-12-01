package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.service.LoginService;
import net.mamoe.mirai.console.command.CommandExecuteResult;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.ListeningStatus;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * 监听群消息
 *
 * @author zzwtsy
 * @since 2022/12/01
 */
public class ListenerGroupMessage extends SimpleListenerHost {
    String message;
    GroupMessageEvent event;

    @EventHandler
    private ListeningStatus onEvent(GroupMessageEvent event) {
        this.event = event;
        message = event.getMessage().contentToString();
        if (message.startsWith("login")){

        }
        return ListeningStatus.LISTENING;
    }
}
