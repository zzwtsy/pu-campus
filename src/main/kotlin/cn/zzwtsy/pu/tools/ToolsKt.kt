package cn.zzwtsy.pu.tools

import cn.zzwtsy.pu.utils.EncryptionKt

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

    /**
     * md5 加密 qq id
     * @param [qqId] qq id
     * @return [String]
     */
    fun encryptionQqIdToMd5(qqId: Long): String {
        val reversedQqId = qqId.toString().reversed()
        val salt = reversedQqId.substring(0, 4)
        return EncryptionKt.toMd5(reversedQqId, salt)
    }
}