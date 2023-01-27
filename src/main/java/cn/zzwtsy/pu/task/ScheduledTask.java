package cn.zzwtsy.pu.task;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.UserBean;
import cn.zzwtsy.pu.service.UserService;
import cn.zzwtsy.pu.service.event.EventService;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.contact.Friend;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.AtAll;
import net.mamoe.mirai.message.data.MessageChain;

import static cn.zzwtsy.pu.tools.MyStatic.settingBean;
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
        long userId;
        Bot bot = Bot.getInstance(settingBean.getBotId());
        //获取qq机器人的管理员好友
        Friend botFriend = bot.getFriend(settingBean.getAdminId());
        //判断获取好友是否失败
        if (botFriend == null) {
            PuCampus.INSTANCE.getLogger().error("以管理员 QQ 号码获取机器人好友对象失败");
            return;
        }
        //判断公共token是否存在，不存在则使用管理员token
        if (!checkUserLogin(0)) {
            userBean = new UserService().getUser(settingBean.getAdminId());
            if (userBean == null) {
                botFriend.sendMessage("请添加公共 pu 账号或管理员登录 pu 校园，以启用定时推送");
                return;
            }
            userId = settingBean.getAdminId();
        } else {
            userId = 0;
        }
        Group group = bot.getGroup(settingBean.getGroupId());
        //判断获取qq群是否失败
        if (group != null) {
            MessageChain messages = new EventService(userId).getNewEventList();
            group.sendMessage(AtAll.INSTANCE.plus("\n")
                    .plus("今日可参加活动列表")
                    .plus("\n\n")
                    .plus(messages));
        } else {
            PuCampus.INSTANCE.getLogger().error("获取qq群失败，请检查配置文件中的qq群号");
            botFriend.sendMessage("发送定时信息失败：获取qq群失败，请检查配置文件中的qq群号");
        }
    }
}
