package cn.zzwtsy.pu.utils

/**
 * 正则表达式检查
 * @author zzwtsy
 * @date 2023/03/20
 */
object RegexUtil {
    /**
     * 检查时间，格式（23-00）
     * @param [time] 时间
     * @return [Boolean]
     */
    @JvmStatic
    fun checkTime(time: String): Boolean {
        val regex = Regex("^(?:[01]\\d|2[0-3]):[0-5]\\d\$")
        return regex.containsMatchIn(time)
    }

    /**
     * 检查用户 qq 号是否正确
     * @param [qq] qq
     * @return [Boolean]
     */
    @JvmStatic
    fun checkUserQqId(qq: Long): Boolean {
        val regex = Regex("^[1-9][0-9]{4,10}\$")
        return regex.containsMatchIn(qq.toString())
    }

    /**
     * 检查日期，格式（yyyy-MM-dd）
     * @param [date] 日期
     * @return [Boolean]
     */
    @JvmStatic
    fun checkDateFormat(date: String): Boolean {
        val regex =
            Regex("^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)\$")
        return regex.containsMatchIn(date)
    }
}