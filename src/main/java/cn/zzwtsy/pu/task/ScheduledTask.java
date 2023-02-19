package cn.zzwtsy.pu.task;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.data.Setting;
import cn.zzwtsy.pu.service.event.EventService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.MessageChain;

import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;

/**
 * 定时任务
 *
 * @author zzwtsy
 * @since 2023/01/10
 */
public class ScheduledTask implements Runnable {

    @Override
    public void run() {
        long userId = 0;
        //判断公共token是否存在
        if (checkUserLogin(userId)) {
            PuCampus.INSTANCE.getLogger().error("运行定时任务失败，不存在公共Token");
            return;
        }
        Bot bot = Bot.getInstance(Setting.INSTANCE.getBotId());
        Group group = bot.getGroup(Setting.INSTANCE.getGroupId());
        //判断获取qq群是否失败
        if (group == null) {
            PuCampus.INSTANCE.getLogger().error("运行定时任务失败，获取qq群失败");
            return;
        }
        String messages = new EventService(userId).getNewEventList();
        MessageChain messageChain;
        if (messages.isEmpty()) {
            messageChain = AtAll.INSTANCE.plus("\n")
                    .plus("今日暂无可参加活动");
        } else {
            messageChain = AtAll.INSTANCE.plus("\n")
                    .plus("今日可参加活动")
                    .plus("\n\n")
                    .plus(messages);
        }
        group.sendMessage(messageChain);
    }
}
