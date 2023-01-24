package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.service.LoginService;
import cn.zzwtsy.pu.service.TimedTaskService;
import cn.zzwtsy.pu.service.UserService;
import cn.zzwtsy.pu.tools.SaveConfig;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupTempMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import static cn.zzwtsy.pu.tools.MyStatic.command;
import static cn.zzwtsy.pu.tools.MyStatic.setting;
import static cn.zzwtsy.pu.tools.Tools.checkAdminQqId;
import static cn.zzwtsy.pu.tools.Tools.checkTime;
import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;
import static cn.zzwtsy.pu.tools.Tools.checkUserQqId;
import static cn.zzwtsy.pu.tools.Tools.splitMessage;

/**
 * 监听私聊消息
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class ListenerPrivateChatMessage extends SimpleListenerHost {
    private final String commandPrefix = command.getPublicX().getCommandPrefix();
    private final String addPublicToken = commandPrefix + command.getAdmin().getAddPublicToken();
    private final String loginCommand = commandPrefix + command.getPrivateX().getLogin();
    private final String deleteUserCommand = commandPrefix + command.getPrivateX().getDeleteUser();
    private final String adminDeleteUserCommand = commandPrefix + command.getAdmin().getAdminDeleteUser();
    private final String timedTaskCommand = commandPrefix + command.getAdmin().getTimedTask();
    private final String helpCommand = commandPrefix + command.getPublicX().getHelp();
    String message;
    FriendMessageEvent friendMessageEvent;
    GroupTempMessageEvent groupTempMessageEvent;

    /**
     * 机器人收到的好友消息的事件
     *
     * @param event 事件
     */
    @EventHandler
    private void onEvent(FriendMessageEvent event) {
        this.friendMessageEvent = event;
        message = friendMessageEvent.getMessage().contentToString();
        run(friendMessageEvent);
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
        run(groupTempMessageEvent);
    }

    private void run(MessageEvent messageEvent) {
        long userQqId = messageEvent.getSender().getId();
        //登陆命令
        if (message.startsWith(loginCommand)) {
            login(message, messageEvent, userQqId);
            return;
        }
        //用户删除自己信息
        if (message.startsWith(deleteUserCommand)) {
            deleteUser(messageEvent, userQqId);
            return;
        }
        if (message.startsWith(helpCommand)) {
            if (checkAdminQqId(userQqId)) {
                messageEvent.getSender().sendMessage("===管管理员命令===\n" + new HelpInfo().adminHelpInfo());
            } else {
                messageEvent.getSender().sendMessage("===私聊命令===\n" + new HelpInfo().privateHelpInfo());
            }
            return;
        }
        //判断用户是否有实用管理员命令权限
        if (checkAdminQqId(userQqId)) {
            messageEvent.getSender().sendMessage("你没有此命令权限");
        } else {
            //管理员删除用户信息（可删除所有用户信息）
            if (message.startsWith(adminDeleteUserCommand)) {
                adminDeleteUser(message, messageEvent);
                return;
            }
            //添加公共Token
            if (message.startsWith(addPublicToken)) {
                login(message, messageEvent, 0);
                return;
            }
            //定时任务
            if (message.startsWith(timedTaskCommand)) {
                String[] strings = splitMessage(message);
                TimedTaskService timedTaskService = new TimedTaskService();
                switch (strings[1]) {
                    case "关闭":
                    case "0":
                        setting.setTimedTaskTime("0");
                        SaveConfig.saveSettingConfig(setting);
                        timedTaskService.stop();
                        break;
                    default:
                        if (checkTime(strings[1])) {
                            messageEvent.getSender().sendMessage("时间格式错误");
                            break;
                        }
                        setting.setTimedTaskTime(strings[1]);
                        SaveConfig.saveSettingConfig(setting);
                        timedTaskService.start();
                        break;
                }
            }
        }
    }

    /**
     * 登录
     *
     * @param message      消息
     * @param messageEvent 消息事件
     */
    private void login(String message, MessageEvent messageEvent, long userQqId) {
        messageEvent.getSender().sendMessage("正在登录,请稍后...");
        String[] strings = splitMessage(message);
        //补全用户账号: 用户账号加用户学校邮件后缀
        String userName = strings[1] + setting.getEmailSuffix();
        String getUserTokenStatus = new LoginService().getUserToken(userQqId, userName, strings[2]);
        messageEvent.getSender().sendMessage(getUserTokenStatus);
    }

    /**
     * 用户删除自己的信息
     *
     * @param messageEvent 消息事件
     */
    private void deleteUser(MessageEvent messageEvent, long userQqId) {
        if (!checkUserLogin(userQqId)) {
            messageEvent.getSender().sendMessage("无法删除，没有你的用户信息");
            return;
        }
        int delUserStatus = new UserService().deleteUser(userQqId);
        if (delUserStatus <= 0) {
            messageEvent.getSender().sendMessage("删除用户信息失败");
        } else {
            messageEvent.getSender().sendMessage("删除用户信息成功");
        }
    }

    /**
     * 管理员删除用户信息
     *
     * @param message      消息
     * @param messageEvent 消息事件
     */
    private void adminDeleteUser(String message, MessageEvent messageEvent) {
        long qqId = Long.parseLong(splitMessage(message)[1]);
        if (!checkUserQqId(String.valueOf(qqId))) {
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
