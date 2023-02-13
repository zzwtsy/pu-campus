package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.service.command.CommandService;
import cn.zzwtsy.pu.tools.Tools;
import kotlin.coroutines.CoroutineContext;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.MessageChain;
import org.jetbrains.annotations.NotNull;

import static cn.zzwtsy.pu.tools.CommandConsts.addPublicToken;
import static cn.zzwtsy.pu.tools.CommandConsts.adminDeleteUserCommand;
import static cn.zzwtsy.pu.tools.CommandConsts.showTaskCommand;
import static cn.zzwtsy.pu.tools.CommandConsts.timedTaskCommand;
import static cn.zzwtsy.pu.tools.Tools.checkAdminQqId;

/**
 * 监听私聊消息
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class ListenerPrivateChatMessage extends SimpleListenerHost {
    final String[] adminCommandArrays = {adminDeleteUserCommand, timedTaskCommand, addPublicToken, showTaskCommand};
    final CommandService commandService;
    String message;
    FriendMessageEvent friendMessageEvent;
    GroupTempMessageEvent groupTempMessageEvent;

    public ListenerPrivateChatMessage() {
        commandService = new CommandService();
    }

    /**
     * 机器人收到的好友消息的事件
     *
     * @param event 事件
     */
    @EventHandler
    private void onEvent(FriendMessageEvent event) {
        this.friendMessageEvent = event;
        message = friendMessageEvent.getMessage().contentToString();
        long userQqId = friendMessageEvent.getSender().getId();
        publicCode(userQqId, friendMessageEvent);
    }

    /**
     * 群临时会话消息
     *
     * @param event 事件
     */
    @EventHandler
    private void onEvent(GroupTempMessageEvent event) {
        this.groupTempMessageEvent = event;
        message = groupTempMessageEvent.getMessage().contentToString();
        long userQqId = groupTempMessageEvent.getSender().getId();
        publicCode(userQqId, groupTempMessageEvent);
    }

    /**
     * 公共代码
     *
     * @param userQqId     用户qq
     * @param messageEvent 消息事件
     */
    private void publicCode(long userQqId, MessageEvent messageEvent) {
        //判断命令是否是管理员命令
        if (Tools.messageContainsCommand(message, adminCommandArrays)) {
            //判断用户是否有管理员命令权限
            if (!checkAdminQqId(userQqId)) {
                messageEvent.getSender().sendMessage("你没有此命令权限");
                return;
            }
            long publicTokenQqId = 0;
            MessageChain adminMessage = commandService.adminCommand(message, publicTokenQqId);
            if (adminMessage != null) {
                messageEvent.getSender().sendMessage(adminMessage);
            }
        } else {
            //处理私聊命令
            MessageChain privateMessage = commandService.privateChatCommand(message, userQqId);
            if (privateMessage != null) {
                messageEvent.getSender().sendMessage(privateMessage);
            }
        }
    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        PuCampus.INSTANCE.getLogger().error("发生错误:", exception);
    }
}

