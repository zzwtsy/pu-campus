package cn.zzwtsy.pu.tools

object ToolsKt {
    /**
     * 判断消息是否包含命令
     * @param [message] 消息
     * @param [command] 命令
     * @return [Boolean]
     */
    @JvmStatic
    fun messageContainsCommand(message: String, command: Array<String>): Boolean {
        return command.contains(message)
    }

    /**
     * 以空格拆分消息
     * @param [message] 消息
     * @return [Array<String>]
     */
    @JvmStatic
    fun splitMessage(message: String): Array<String> {
        return message.split(" ").toTypedArray()
    }
}