package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.service.LoginService;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import static cn.zzwtsy.pu.tools.MyStatic.command;
import static cn.zzwtsy.pu.tools.MyStatic.setting;
import static cn.zzwtsy.pu.tools.SplitMessage.splitMessage;

/**
 * 监听私聊消息
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class ListenerPrivateChatMessage extends SimpleListenerHost {
    private final String LOGIN_COMMAND = command.getCommandPrefix() + command.getLogin();
    String message;
    FriendMessageEvent friendMessageEvent;
    GroupTempMessageEvent groupTempMessageEvent;

    @EventHandler
    private void onEvent(FriendMessageEvent event) {
        this.friendMessageEvent = event;
        message = friendMessageEvent.getMessage().contentToString();
        login(message, friendMessageEvent);
    }

    @EventHandler
    private void onEvent(GroupTempMessageEvent event) {
        this.groupTempMessageEvent = event;
        message = groupTempMessageEvent.getMessage().contentToString();
        login(message, groupTempMessageEvent);
    }

    private void login(String message, MessageEvent messageEvent) {
        if (message.startsWith(LOGIN_COMMAND)) {
            messageEvent.getSender().sendMessage("正在登录,请稍后...");
            String[] strings = splitMessage(message);
            //补全用户账号: 用户账号加用户学校邮件后缀
            String userName = strings[1] + setting.getEmailSuffix();
            long userQqId = messageEvent.getSender().getId();
            String getUserTokenStatus = new LoginService().getUserToken(String.valueOf(userQqId), userName, strings[2]);
            if (!"true".equals(getUserTokenStatus)) {
                messageEvent.getSender().sendMessage(getUserTokenStatus);
            } else {
                messageEvent.getSender().sendMessage("登录成功");
            }
        }
    }
}
