package cn.zzwtsy.pu.task;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.User;
import cn.zzwtsy.pu.service.EventListService;
import cn.zzwtsy.pu.service.UserService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.AtAll;

import static cn.zzwtsy.pu.tools.MyStatic.setting;
import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;

/**
 * 定时任务
 *
 * @author zzwtsy
 * @since 2023/01/10
 */
public class ScheduledTask implements Runnable {
    User user;

    /**
     * 计划任务
     */
    public ScheduledTask() {
        user = new User();
    }

    @Override
    public void run() {
        //判断公共token是否存在，不存在则使用管理员token
        if (!checkUserLogin(0)) {
            user = new UserService().getUser(setting.getAdminId());
        } else {
            user = new UserService().getUser(0);
        }
        Bot bot = Bot.getInstance(setting.getBotId());
        Group group = bot.getGroup(setting.getGroupId());
        if (group != null) {
            String newEventList = new EventListService().getNewEventList(setting.getAdminId());
            group.sendMessage(AtAll.INSTANCE + "\n" + newEventList);
        } else {
            PuCampus.INSTANCE.getLogger().error("获取qq群失败，请检查配置文件中的qq群号");
            Friend botFriend = bot.getFriend(setting.getAdminId());
            if (botFriend != null) {
                botFriend.sendMessage("发送定时信息失败：获取qq群失败，请检查配置文件中的qq群号");
            } else {
                PuCampus.INSTANCE.getLogger().error("以管理员 QQ 号码获取机器人好友对象失败");
            }
        }
    }
}
