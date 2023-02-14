package cn.zzwtsy.pu.utils

import java.io.File

object FileUtil {
    /**
     * 读文件
     * @param [path] 路径
     * @return [String]
     */
    @JvmStatic
    fun readFile(path: String): String {
        return File(path).readText()
    }

    /**
     * 写文件
     * @param [path] 路径
     * @param [content] 内容
     */
    @JvmStatic
    fun writeFile(path: String, content: String) {
        File(path).writeText(content)
    }
}