package cn.zzwtsy.pu.listener;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.service.LoginService;
import cn.zzwtsy.pu.tools.SaveConfig;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import static cn.zzwtsy.pu.tools.MyStatic.command;
import static cn.zzwtsy.pu.tools.MyStatic.setting;

/**
 * 监听群消息
 *
 * @author zzwtsy
 * @since 2022/12/01
 */
public class ListenerGroupMessage extends SimpleListenerHost {
    private final String LOGIN_COMMAND = command.getCommandPrefix() + command.getLogin();
    private final String EVENT_LIST = command.getCommandPrefix() + command.getGetCalendarEventList();
    String message;
    GroupMessageEvent event;

    @EventHandler
    private void onEvent(GroupMessageEvent event) {
        this.event = event;
        message = event.getMessage().contentToString();
        if (message.startsWith(LOGIN_COMMAND)) {
            String[] strings = splitMessage(message);
            String userName = strings[1] + setting.getEmailSuffix();
            String userToken = new LoginService().getUserToken(userName, strings[2]);
            if (!"true".equals(userToken)) {
                event.getGroup().sendMessage(userToken);
            } else {
                try {
                    SaveConfig.saveUserConfig();
                } catch (IOException e) {
                    PuCampus.INSTANCE.getLogger().error("保存用户Token失败");
                }
                event.getGroup().sendMessage("登录成功");
            }
        }
        if (message.startsWith(EVENT_LIST)) {
            String[] strings = splitMessage(message);
        }
    }

    /**
     * 拆分消息
     *
     * @param message 消息
     * @return {@link String[]}
     */
    private String[] splitMessage(@NotNull String message) {
        return message.split(" ");
    }
}
