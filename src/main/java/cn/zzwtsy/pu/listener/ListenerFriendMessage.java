package cn.zzwtsy.pu.listener;


import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.service.LoginService;
import cn.zzwtsy.pu.tools.SaveConfig;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.FriendMessageEvent;

import java.io.IOException;

import static cn.zzwtsy.pu.tools.MyStatic.command;
import static cn.zzwtsy.pu.tools.MyStatic.setting;
import static cn.zzwtsy.pu.tools.SplitMessage.splitMessage;

/**
 * 监听好友消息
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class ListenerFriendMessage extends SimpleListenerHost {
    private final String LOGIN_COMMAND = command.getCommandPrefix() + command.getLogin();
    String message;
    FriendMessageEvent event;

    @EventHandler
    private void onEvent(FriendMessageEvent event) {
        this.event = event;
        message = event.getMessage().contentToString();
        if (message.startsWith(LOGIN_COMMAND)) {
            String[] strings = splitMessage(message);
            //补全用户账号: 用户账号加用户学校邮件后缀
            String userName = strings[1] + setting.getEmailSuffix();
            long userQqId = event.getSender().getId();
            String getUserTokenStatus = new LoginService().getUserToken(String.valueOf(userQqId), userName, strings[2]);
            if (!"true".equals(getUserTokenStatus)) {
                event.getFriend().sendMessage(getUserTokenStatus);
            } else {
                try {
                    SaveConfig.saveUserConfig();
                } catch (IOException e) {
                    PuCampus.INSTANCE.getLogger().error("保存用户Token失败");
                }
                event.getFriend().sendMessage("登录成功");
            }
        }
    }
}
