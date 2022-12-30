package cn.zzwtsy.pu.tools;

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
    public static String[] splitMessage(String message) {
        return message.split(" ");
    }
}
