package cn.zzwtsy.pu.tools;

import org.jetbrains.annotations.NotNull;

/**
 * 拆分消息
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class SplitMessage {
    /**
     * 拆分消息
     *
     * @param message 消息
     * @return {@link String[]}
     */
    public static String[] splitMessage(@NotNull String message) {
        return message.split(" ");
    }
}
