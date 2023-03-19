package cn.zzwtsy.pu.utils

import java.time.*
import java.time.format.DateTimeFormatter

object DateUtilKt {
    /**
     * unix 时间戳格式化
     * @param [unixTimestamp] unix时间戳
     * @return [String] yyyy-MM-dd HH:mm
     */
    @JvmStatic
    fun unixTimestampToDateTime(unixTimestamp: Long): String {
        // 将 Unix 时间戳转换成 Instant 对象
        val instant = Instant.ofEpochSecond(unixTimestamp)
        // 获取系统默认时区
        val zoneId = ZoneId.systemDefault()
        // 将 Instant 对象转换成 LocalDateTime 对象
        val localDateTime = LocalDateTime.ofInstant(instant, zoneId)
        // 定义日期时间格式
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        // 将 LocalDateTime 对象格式化为字符串
        return localDateTime.format(formatter)
    }

    /**
     * 日期时间转换为 unix 时间戳
     * @param [dateTime] 日期时间
     * @return [Long]
     */
    @JvmStatic
    fun dateTimeToUnixTimestamp(dateTime: String): Long {
        // 定义日期时间格式
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        // 将字符串解析成 LocalDateTime 对象
        val localDateTime = LocalDateTime.parse(dateTime, formatter)
        // 获取系统默认时区
        val zoneId = ZoneId.systemDefault()
        // 将 LocalDateTime 对象转换成 Instant 对象
        val instant = localDateTime.atZone(zoneId).toInstant()
        // 返回 Unix 时间戳
        return instant.epochSecond
    }

    /**
     * 日期计算（天）
     * @param [date] 日期
     * @param [days] 天
     * @return [LocalDate]
     */
    @JvmStatic
    fun daysCalculation(date: LocalDate, days: Int): LocalDate {
        return date.plusDays(days.toLong())
    }

    /**
     * 日期计算（周）
     * @param [date] 日期
     * @param [weeks] 周
     * @return [LocalDate]
     */
    @JvmStatic
    fun weeksCalculation(date: LocalDate, weeks: Int): LocalDate {
        return date.plusWeeks(weeks.toLong())
    }

    /**
     * 日期计算（月）
     * @param [date] 日期
     * @param [months] 月
     * @return [LocalDate]
     */
    @JvmStatic
    fun monthsCalculation(date: LocalDate, months: Int): LocalDate {
        return date.plusMonths(months.toLong())
    }

    /**
     * 日期计算（年）
     * @param [date] 日期
     * @param [years] 年
     * @return [LocalDate]
     */
    @JvmStatic
    fun yearsCalculation(date: LocalDate, years: Int): LocalDate {
        return date.plusYears(years.toLong())
    }

    /**
     * 加一年
     * @param [date] 日期
     * @return [String]
     */
    @JvmStatic
    fun makeUpForTheWholeYear(date: String): String {
        return Year.now().toString() + "-" + date
    }

    /**
     * 补全日期
     * @param [time] 时间
     * @return [String]
     */
    @JvmStatic
    fun complementaryDate(time: String): String {
        val now = LocalDateTime.now()
        return "${now.year}-${now.monthValue}-${now.dayOfMonth} ${time}:00"
    }

    /**
     * 检查日期格式
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