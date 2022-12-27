package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.service.LoginService;
import cn.zzwtsy.pu.service.UserService;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static cn.zzwtsy.pu.tools.CheckUser.*;
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
    private final String loginCommand = command.getCommandPrefix() + command.getLogin();
    private final String deleteUserCommand = command.getCommandPrefix() + command.getDeleteUser();
    private final String adminDeleteUserCommand = command.getCommandPrefix() + command.getAdminDeleteUser();
    String message;
    FriendMessageEvent friendMessageEvent;
    GroupTempMessageEvent groupTempMessageEvent;
    ThreadPoolExecutor executor = new ThreadPoolExecutor(2,
            5,
            10,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(2),
            new ThreadPoolExecutor.DiscardPolicy());

    @EventHandler
    private void onEvent(FriendMessageEvent event) {
        this.friendMessageEvent = event;
        message = friendMessageEvent.getMessage().contentToString();
        run(friendMessageEvent);
    }

    @EventHandler
    private void onEvent(GroupTempMessageEvent event) {
        this.groupTempMessageEvent = event;
        message = groupTempMessageEvent.getMessage().contentToString();
        run(groupTempMessageEvent);
    }

    private void run(MessageEvent messageEvent) {
        executor.execute(() -> {
            if (message.startsWith(loginCommand)) {
                login(message, messageEvent);
                return;
            }
            if (message.startsWith(deleteUserCommand)) {
                deleteUser(messageEvent);
                return;
            }
            if (message.startsWith(adminDeleteUserCommand) &&
                    checkAdminQqId(messageEvent.getSender().getId())) {
                adminDeleteUser(message, messageEvent);
            }
        });
    }

    /**
     * 登录
     *
     * @param message      消息
     * @param messageEvent 消息事件
     */
    private void login(String message, MessageEvent messageEvent) {
        messageEvent.getSender().sendMessage("正在登录,请稍后...");
        String[] strings = splitMessage(message);
        //补全用户账号: 用户账号加用户学校邮件后缀
        String userName = strings[1] + setting.getEmailSuffix();
        long userQqId = messageEvent.getSender().getId();
        String getUserTokenStatus = new LoginService().getUserToken(String.valueOf(userQqId), userName, strings[2]);
        messageEvent.getSender().sendMessage(getUserTokenStatus);
    }

    /**
     * 删除用户
     *
     * @param messageEvent 消息事件
     */
    private void deleteUser(MessageEvent messageEvent) {
        long userQqId = messageEvent.getSender().getId();
        if (!checkUserLogin(String.valueOf(userQqId))) {
            messageEvent.getSender().sendMessage("无法删除，没有你的用户信息");
            return;
        }
        int delUserStatus = new UserService().deleteUser(String.valueOf(userQqId));
        if (delUserStatus <= 0) {
            messageEvent.getSender().sendMessage("删除用户信息失败");
        } else {
            messageEvent.getSender().sendMessage("删除用户信息成功");
        }
    }

    /**
     * 管理员删除用户
     *
     * @param message      消息
     * @param messageEvent 消息事件
     */
    private void adminDeleteUser(String message, MessageEvent messageEvent) {
        String qqId = splitMessage(message)[1];
        if (!checkUserQqId(qqId)) {
            messageEvent.getSender().sendMessage("用户『" + qqId + "』qq号错误");
            return;
        }
        if (!checkUserLogin(qqId)) {
            messageEvent.getSender().sendMessage("没有『" + qqId + "』用户信息");
            return;
        }
        int deleteUserStatus = new UserService().deleteUser(qqId);
        if (deleteUserStatus <= 0) {
            messageEvent.getSender().sendMessage("删除『" + qqId + "』用户信息失败");
        } else {
            messageEvent.getSender().sendMessage("删除『" + qqId + "』用户信息成功");
        }
    }
}
