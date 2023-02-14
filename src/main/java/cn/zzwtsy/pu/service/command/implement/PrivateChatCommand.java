package cn.zzwtsy.pu.service.command.implement;

import cn.zzwtsy.pu.service.UserService;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import static cn.zzwtsy.pu.tools.CommandConsts.DELETE_USER_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.HELP_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.LOGIN_COMMAND;
import static cn.zzwtsy.pu.tools.Tools.checkAdminQqId;
import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;

/**
 * 私人聊天
 *
 * @author zzwtsy
 * @since 2023/01/26
 */
public class PrivateChatCommand extends AbstractCommand {

    @Override
    public MessageChain processingCommand(String message, long userQqId) {
        //登陆命令
        if (message.startsWith(LOGIN_COMMAND)) {
            return new MessageChainBuilder()
                    .append(login(message, userQqId))
                    .build();
        }
        //用户删除自己信息
        if (message.startsWith(DELETE_USER_COMMAND)) {
            return new MessageChainBuilder()
                    .append(deleteUser(userQqId))
                    .build();
        }
        if (message.startsWith(HELP_COMMAND)) {
            if (checkAdminQqId(userQqId)) {
                return new MessageChainBuilder()
                        .append("===管理员命令===\n\n")
                        .append(adminHelpInfo())
                        .build();
            }
            return new MessageChainBuilder()
                    .append("===私聊命令===\n\n")
                    .append(privateHelpInfo())
                    .build();
        }
        return null;
    }

    /**
     * 用户删除自己的信息
     *
     * @param userQqId 用户qq id
     * @return {@link String}
     */
    private String deleteUser(long userQqId) {
        if (checkUserLogin(userQqId)) {
            return "无法删除，没有你的用户信息";
        }
        int delUserStatus = new UserService().deleteUser(userQqId);
        if (delUserStatus <= 0) {
            return "删除用户信息失败";
        } else {
            return "删除用户信息成功";
        }
    }
}
