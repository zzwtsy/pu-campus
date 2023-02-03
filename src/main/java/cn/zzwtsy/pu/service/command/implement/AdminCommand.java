package cn.zzwtsy.pu.service.command.implement;

import cn.zzwtsy.pu.service.TimedTaskService;
import cn.zzwtsy.pu.service.UserService;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import static cn.zzwtsy.pu.tools.CommandConsts.addPublicToken;
import static cn.zzwtsy.pu.tools.CommandConsts.adminDeleteUserCommand;
import static cn.zzwtsy.pu.tools.CommandConsts.showTaskCommand;
import static cn.zzwtsy.pu.tools.CommandConsts.timedTaskCommand;
import static cn.zzwtsy.pu.tools.Tools.checkTime;
import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;
import static cn.zzwtsy.pu.tools.Tools.checkUserQqId;
import static cn.zzwtsy.pu.tools.Tools.splitMessage;

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
        if (message.startsWith(adminDeleteUserCommand)) {
            return new MessageChainBuilder()
                    .append(adminDeleteUser(message))
                    .build();
        }
        //添加公共Token
        if (message.startsWith(addPublicToken)) {
            return new MessageChainBuilder()
                    .append(login(message, userQqId))
                    .build();
        }
        //定时任务
        if (message.startsWith(timedTaskCommand)) {
            return new MessageChainBuilder()
                    .append(timedTask(message))
                    .build();
        }
        if (message.startsWith(showTaskCommand)){
            return new MessageChainBuilder()
                    .append(new TimedTaskService().showTaskStatus())
                    .build();
        }
        return null;
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
        String qqIdStr = strings[1];
        if (!checkUserQqId(qqIdStr)) {
            return "用户『" + qqId + "』qq号错误";
        }
        if (checkUserLogin(qqId)) {
            return "没有『" + qqId + "』用户信息";
        }
        int deleteUserStatus = new UserService().deleteUser(qqId);
        if (deleteUserStatus <= 0) {
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
        int commandMaxLength = 3;
        int commandMinLength = 2;
        String[] strings = splitMessage(message);
        if (strings.length > commandMaxLength || strings.length < commandMinLength) {
            return "命令格式错误";
        }
        long groupId = Long.parseLong(strings[2]);
        String time = strings[1];
        String closeTimedTask = "关闭";
        if (closeTimedTask.equals(time)) {
            return new TimedTaskService().stopTimedTask(groupId);
        }
        if (!checkTime(time)) {
            return "时间格式错误";
        }
        return new TimedTaskService().startTimedTask(groupId);
    }

}
