package cn.zzwtsy.pu.service.command;

import cn.zzwtsy.pu.service.command.implement.AdminCommand;
import cn.zzwtsy.pu.service.command.implement.GroupCommand;
import cn.zzwtsy.pu.service.command.implement.PrivateChatCommand;
import net.mamoe.mirai.message.data.MessageChain;

/**
 * 处理命令
 *
 * @author zzwtsy
 * @since 2023/01/26
 */
public class CommandService {
    /**
     * 群组命令
     *
     * @param message  消息
     * @param userQqId 用户qq id
     * @return {@link MessageChain}
     */
    public MessageChain groupCommand(String message, long userQqId) {
        return new GroupCommand().processingCommand(message, userQqId);
    }

    /**
     * 私聊命令
     *
     * @param message  消息
     * @param userQqId 用户qq id
     * @return {@link MessageChain}
     */
    public MessageChain privateChatCommand(String message, long userQqId) {
        return new PrivateChatCommand().processingCommand(message, userQqId);
    }

    /**
     * 管理员命令
     *
     * @param message  消息
     * @param userQqId 用户qq id
     * @return {@link MessageChain}
     */
    public MessageChain adminCommand(String message, long userQqId) {
        return new AdminCommand().processingCommand(message, userQqId);
    }
}
