package cn.zzwtsy.pu.utils

import java.security.MessageDigest

/**
 * 加密
 * @author zzwtsy
 * @date 2023/03/19
 */
object EncryptionKt {
    /**
     * md5
     * @param [content] 内容
     * @param [salt] 盐
     * @return [String]
     */
    @JvmStatic
    fun toMd5(content: String, salt: String): String {
        val hash = MessageDigest.getInstance("MD5").digest((content + salt).toByteArray())
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            var str = Integer.toHexString(b.toInt())
            if (b < 0x10) {
                str = "0$str"
            }
            hex.append(str.substring(str.length - 2))
        }
        return hex.toString()
    }
}