package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.service.command.CommandService;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

/**
 * 监听群消息
 *
 * @author zzwtsy
 * @since 2022/12/01
 */
public class ListenerGroupMessage extends SimpleListenerHost {
    String message;
    GroupMessageEvent groupMessageEvent;

    @EventHandler
    private void onEvent(GroupMessageEvent event) {
        this.groupMessageEvent = event;
        message = groupMessageEvent.getMessage().contentToString();
        long userQqId = groupMessageEvent.getSender().getId();
        MessageChain messageChain = new CommandService().groupCommand(message, userQqId);
        if (messageChain != null) {
            groupMessageEvent.getGroup().sendMessage(messageChain);
        }
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        PuCampus.INSTANCE.getLogger().error("发生错误:", exception);
    }
}
