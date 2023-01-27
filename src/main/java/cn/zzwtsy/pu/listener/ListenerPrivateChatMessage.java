package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.service.command.CommandService;
import cn.zzwtsy.pu.tools.Tools;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import net.mamoe.mirai.message.data.MessageChain;

import static cn.zzwtsy.pu.tools.MyStatic.commandBean;
import static cn.zzwtsy.pu.tools.Tools.checkAdminQqId;

/**
 * 监听私聊消息
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class ListenerPrivateChatMessage extends SimpleListenerHost {
    private final String commandPrefix = commandBean.getPublicBean().getCommandPrefix();
    private final String addPublicToken = commandPrefix + commandBean.getAdminBean().getAddPublicToken();
    private final String adminDeleteUserCommand = commandPrefix + commandBean.getAdminBean().getAdminDeleteUser();
    private final String timedTaskCommand = commandPrefix + commandBean.getAdminBean().getTimedTask();
    String[] adminCommandArrays = {adminDeleteUserCommand, timedTaskCommand, addPublicToken};
    String message;
    FriendMessageEvent friendMessageEvent;
    GroupTempMessageEvent groupTempMessageEvent;
    CommandService commandService;

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
        MessageChain messageChain = commandService.privateChatCommand(message, userQqId);
        if (messageChain != null) {
            friendMessageEvent.getSender().sendMessage(messageChain);
        }
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
        //处理私聊命令
        MessageChain privateMessage = commandService.privateChatCommand(message, userQqId);
        if (privateMessage != null) {
            groupTempMessageEvent.getSender().sendMessage(privateMessage);
        }
        //判断命令是否是管理员命令
        if (!Tools.messageContainsCommand(message, adminCommandArrays)) {
            return;
        }
        //判断用户是否有管理员命令权限
        if (!checkAdminQqId(userQqId)) {
            groupTempMessageEvent.getSender().sendMessage("你没有此命令权限");
            return;
        }
        long publicTokenQqId = 0;
        MessageChain adminMessage = commandService.adminCommand(message, publicTokenQqId);
        if (adminMessage != null) {
            groupTempMessageEvent.getSender().sendMessage(adminMessage);
        }
    }
}

