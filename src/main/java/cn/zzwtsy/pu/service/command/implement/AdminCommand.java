package cn.zzwtsy.pu.service.command.implement;

import cn.zzwtsy.pu.data.Setting;
import cn.zzwtsy.pu.database.dao.UserDao;
import cn.zzwtsy.pu.service.TimedTaskService;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import static cn.zzwtsy.pu.tools.CommandConsts.ADD_PUBLIC_TOKEN;
import static cn.zzwtsy.pu.tools.CommandConsts.ADMIN_DELETE_USER_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.SHOW_TASK_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.TIMED_TASK_COMMAND;
import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;
import static cn.zzwtsy.pu.tools.Tools.splitMessage;
import static cn.zzwtsy.pu.utils.RegexUtil.checkTime;
import static cn.zzwtsy.pu.utils.RegexUtil.checkUserQqId;

/**
 * 管理员
 *
 * @author zzwtsy
 * @since 2023/01/26
 */
public class AdminCommand extends AbstractCommand {
    /**
     * 处理命令
     *
     * @param message  消息
     * @param userQqId 用户qq
     * @return {@link MessageChain}
     */
    @Override
    public MessageChain processingCommand(String message, long userQqId) {
        //管理员删除用户信息（可删除所有用户信息）
        if (message.startsWith(ADMIN_DELETE_USER_COMMAND)) {
            return new MessageChainBuilder()
                    .append(adminDeleteUser(message))
                    .build();
        }
        //添加公共Token
        if (message.startsWith(ADD_PUBLIC_TOKEN)) {
            return new MessageChainBuilder()
                    .append(login(message, userQqId))
                    .build();
        }
        //定时任务
        if (message.startsWith(TIMED_TASK_COMMAND)) {
            return new MessageChainBuilder()
                    .append(timedTask(message))
                    .build();
        }
        if (message.startsWith(SHOW_TASK_COMMAND)) {
            return new MessageChainBuilder()
                    .append(new TimedTaskService().showTaskStatus())
                    .build();
        }
        return new MessageChainBuilder().build();
    }

    /**
     * 管理员删除用户信息
     *
     * @param message 消息
     */
    private String adminDeleteUser(String message) {
        String[] strings = splitMessage(message);
        int commandLength = 2;
        if (strings.length != commandLength) {
            return "命令格式错误";
        }
        long qqId = Long.parseLong(strings[1]);
        if (!checkUserQqId(qqId)) {
            return "用户『" + qqId + "』qq号错误";
        }
        if (checkUserLogin(qqId)) {
            return "没有『" + qqId + "』用户信息";
        }
        boolean deleteUserStatus = UserDao.INSTANCE.deleteUser(qqId);
        if (deleteUserStatus) {
            return "删除『" + qqId + "』用户信息失败";
        } else {
            return "删除『" + qqId + "』用户信息成功";
        }
    }

    /**
     * 定时任务
     *
     * @param message 消息
     * @return {@link String}
     */
    private String timedTask(String message) {
        int commandLength = 3;
        String[] strings = splitMessage(message);
        if (strings.length != commandLength) {
            return "命令格式错误";
        }
        long groupId = Long.parseLong(strings[2]);
        String time = strings[1];
        String closeTimedTask = "关闭";
        if (closeTimedTask.equals(time)) {
            Setting.INSTANCE.setTimedTask("0");
            return new TimedTaskService().stopTimedTask(groupId);
        }
        if (!checkTime(time)) {
            return "时间格式错误";
        }
        Setting.INSTANCE.setTimedTask(time);
        return new TimedTaskService().startTimedTask(groupId, time);
    }

}
