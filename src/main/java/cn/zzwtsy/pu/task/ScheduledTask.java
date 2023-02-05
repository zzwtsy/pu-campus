package cn.zzwtsy.pu.task;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.UserBean;
import cn.zzwtsy.pu.service.event.EventService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.MessageChain;

import static cn.zzwtsy.pu.tools.Consts.settingBean;
import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;

/**
 * 定时任务
 *
 * @author zzwtsy
 * @since 2023/01/10
 */
public class ScheduledTask implements Runnable {
    UserBean userBean;

    /**
     * 计划任务
     */
    public ScheduledTask() {
        userBean = new UserBean();
    }

    @Override
    public void run() {
        long userId = 0;
        //判断公共token是否存在，不存在则使用管理员token
        if (checkUserLogin(userId)) {
            PuCampus.INSTANCE.getLogger().error("运行定时任务失败，不存在公共Token");
            return;
        }
        Bot bot = Bot.getInstance(settingBean.getBotId());
        Group group = bot.getGroup(settingBean.getGroupId());
        //判断获取qq群是否失败
        if (group != null) {
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
        } else {
            PuCampus.INSTANCE.getLogger().error("运行定时任务失败，获取qq群失败");
        }
    }
}
