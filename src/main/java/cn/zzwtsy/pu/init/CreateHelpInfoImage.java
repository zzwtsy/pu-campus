package cn.zzwtsy.pu.init;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.img.SyntheticHelpInfoImage;

import java.util.HashMap;

import static cn.zzwtsy.pu.tools.MyStatic.command;

/**
 * 创建帮助信息图像
 *
 * @author zzwtsy
 * @since 2023/01/14
 */
public class CreateHelpInfoImage {
    private final SyntheticHelpInfoImage syntheticHelpInfoImage;

    public CreateHelpInfoImage() {
        syntheticHelpInfoImage = new SyntheticHelpInfoImage();
    }

    public void createAllHelpInfoImage() {
        try {
            createGroupHelpInfoImage();
        } catch (Exception e) {
            PuCampus.INSTANCE.getLogger().error("合成 qq 群帮助信息图片失败", e);
        }
        try {
            createPrivateHelpInfoImage();
        } catch (Exception e) {
            PuCampus.INSTANCE.getLogger().error("合成私聊帮助信息图片失败", e);
        }
        try {
            createAdminHelpInfoImage();
        } catch (Exception e) {
            PuCampus.INSTANCE.getLogger().error("合成管理员帮助信息图片失败", e);
        }
    }

    /**
     * 创建 qq 群帮助信息图片
     *
     * @throws Exception 异常
     */
    private void createGroupHelpInfoImage() throws Exception {
        String eventListCommand = command.getCommandPrefix() + command.getGetCalendarEventList();
        String helpCommand = command.getCommandPrefix() + command.getHelp();
        String queryUserCreditInfoCommand = command.getCommandPrefix() + command.getQueryUserCreditInfo();
        String querySignInEventListCommand = command.getCommandPrefix() + command.getQuerySignInEventList();
        String querySignOutEventListCommand = command.getCommandPrefix() + command.getQuerySignOutEventList();
        String queryUserEventEndUnissuedCreditListCommand = command.getCommandPrefix() + command.getQueryUserEventEndUnissuedCreditList();
        HashMap<String, String> command = new HashMap<>(6);
        command.put(eventListCommand, "根据日期获取可参加活动列表");
        command.put(queryUserCreditInfoCommand, "查询个人学分信息");
        command.put(queryUserEventEndUnissuedCreditListCommand, "获取活动已结束未发放学分的活动");
        command.put(querySignInEventListCommand, "获取需要签到的活动");
        command.put(querySignOutEventListCommand, "获取需要签退的活动");
        command.put(helpCommand, "获取帮助信息");
        syntheticHelpInfoImage.syntheticGroupHelpInfoImage(command);
    }

    /**
     * 创建私聊帮助信息图片
     *
     * @throws Exception 异常
     */
    private void createPrivateHelpInfoImage() throws Exception {
        String loginCommand = command.getCommandPrefix() + command.getLogin();
        String deleteUserInfoCommand = command.getCommandPrefix() + command.getDeleteUser();
        String helpCommand = command.getCommandPrefix() + command.getHelp();
        HashMap<String, String> command = new HashMap<>(3);
        command.put(loginCommand + " <账号|oauthToken> <密码|oauthTokenSecret>", "登录 pu 校园账号");
        command.put(deleteUserInfoCommand, "删除保存在服务器上的用户信息");
        command.put(helpCommand, "获取帮助信息");
        syntheticHelpInfoImage.syntheticPrivateHelpInfoImage(command);
    }

    /**
     * 创建管理员帮助信息图片
     *
     * @throws Exception 异常
     */
    private void createAdminHelpInfoImage() throws Exception {
        String adminDeleteUserInfo = command.getCommandPrefix() + command.getAdminDeleteUser();
        String addPublicToken = command.getCommandPrefix() + command.getAddPublicToken();
        String setTinedTaskTime = command.getCommandPrefix() + command.getTimedTask();
        String helpInfo = command.getCommandPrefix() + command.getHelp();
        HashMap<String, String> command = new HashMap<>(4);
        command.put(adminDeleteUserInfo + " <用户 qq 号>", "删除用户信息");
        command.put(addPublicToken + "<oauthToken> <oauthTokenSecret>", "添加公共Token");
        command.put(setTinedTaskTime + " <时间(00:00)>", "设置定时任务时间");
        command.put(helpInfo, "获取帮助信息");
        syntheticHelpInfoImage.syntheticPrivateHelpInfoImage(command);
    }
}
