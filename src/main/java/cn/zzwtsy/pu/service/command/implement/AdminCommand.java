package cn.zzwtsy.pu.service.command.implement;

import cn.zzwtsy.pu.service.TimedTaskService;
import cn.zzwtsy.pu.service.UserService;
import cn.zzwtsy.pu.tools.LoadConfig;
import cn.zzwtsy.pu.tools.SaveConfig;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import static cn.zzwtsy.pu.tools.CommandConsts.addPublicToken;
import static cn.zzwtsy.pu.tools.CommandConsts.adminDeleteUserCommand;
import static cn.zzwtsy.pu.tools.CommandConsts.timedTaskCommand;
import static cn.zzwtsy.pu.tools.Consts.settingBean;
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
        String closeTimedTask = "关闭";
        TimedTaskService timedTaskService = new TimedTaskService();
        if (closeTimedTask.equals(strings[1])) {
            settingBean.setTimedTaskTime("0");
            SaveConfig.saveSettingConfig(settingBean);
            timedTaskService.stop();
            if (timedTaskService.isShutdown()) {
                LoadConfig.loadSettingConfig();
                return "定时任务已停止";
            }
            return "定时任务停止失败";
        }
        if (!checkTime(strings[1])) {
            return "时间格式错误";
        }
        timedTaskService.stop();
        if (timedTaskService.isShutdown()) {
            settingBean.setTimedTaskTime(strings[1]);
            SaveConfig.saveSettingConfig(settingBean);
            LoadConfig.loadSettingConfig();
            return timedTaskService.start();
        }
        return "设置定时任务失败";
    }
}
