package cn.zzwtsy.pu.service.command;

import net.mamoe.mirai.message.data.MessageChain;

/**
 * 命令
 *
 * @author zzwtsy
 * @since 2023/01/26
 */
public interface Command {
    /**
     * 处理命令
     *
     * @param message  消息
     * @param userQqId 用户 qq
     * @return {@link MessageChain}
     */
    MessageChain processingCommand(String message, long userQqId);
}
