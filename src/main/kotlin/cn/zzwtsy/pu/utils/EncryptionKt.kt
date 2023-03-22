package cn.zzwtsy.pu.utils

import java.security.MessageDigest

/**
 * 加密
 * @author zzwtsy
 * @date 2023/03/19
 */
object EncryptionKt {
    /**
     * 使用盐值计算字符串的 MD5 值
     * @param [input] 待加密的字符串
     * @param [salt] salt 盐值
     * @return [String] 字符串的 MD5 值
     */
    @JvmStatic
    fun toMd5(input: String, salt: String): String {
        // 获取 MessageDigest 实例
        val md = MessageDigest.getInstance("MD5")
        // 将字符串和盐值拼接在一起，然后转换成字节数组
        val byteArray = (input + salt).toByteArray(Charsets.UTF_8)
        // 计算 MD5 值
        val digest = md.digest(byteArray)
        // 将结果转换成十六进制字符串
        return digest.joinToString("") { "%02x".format(it) }
    }
}