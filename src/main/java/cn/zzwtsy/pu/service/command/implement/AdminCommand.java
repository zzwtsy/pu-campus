package cn.zzwtsy.pu.service.command.implement;

import cn.zzwtsy.pu.service.TimedTaskService;
import cn.zzwtsy.pu.service.UserService;
import cn.zzwtsy.pu.service.command.Command;
import cn.zzwtsy.pu.tools.SaveConfig;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import static cn.zzwtsy.pu.tools.MyStatic.commandBean;
import static cn.zzwtsy.pu.tools.MyStatic.settingBean;
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
public class AdminCommand implements Command {
    private final String commandPrefix = commandBean.getPublicBean().getCommandPrefix();
    private final String adminDeleteUserCommand = commandPrefix + commandBean.getAdminBean().getAdminDeleteUser();
    private final String timedTaskCommand = commandPrefix + commandBean.getAdminBean().getTimedTask();
    private final String addPublicToken = commandPrefix + commandBean.getAdminBean().getAddPublicToken();

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
                    .append(new CommandPublicMethod().login(message, userQqId))
                    .build();
        }
        //定时任务
        if (message.startsWith(timedTaskCommand)) {
            return new MessageChainBuilder()
                    .append(timedTask(message))
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
        if (!checkUserQqId(String.valueOf(qqId))) {
            return "用户『" + qqId + "』qq号错误";
        }
        if (!checkUserLogin(qqId)) {
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
        int commandLength = 2;
        String[] strings = splitMessage(message);
        if (strings.length != commandLength) {
            return "命令格式错误";
        }
        TimedTaskService timedTaskService = new TimedTaskService();
        String closeTimedTask = "关闭";
        if (closeTimedTask.equals(strings[1])) {
            settingBean.setTimedTaskTime("0");
            SaveConfig.saveSettingConfig(settingBean);
            timedTaskService.stop();
            return "定时任务已停止";
        }
        if (!checkTime(strings[1])) {
            return "时间格式错误";
        }
        settingBean.setTimedTaskTime(strings[1]);
        SaveConfig.saveSettingConfig(settingBean);
        return timedTaskService.start();
    }
}
