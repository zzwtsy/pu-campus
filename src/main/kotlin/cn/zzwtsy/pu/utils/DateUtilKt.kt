package cn.zzwtsy.pu.utils

import java.time.*
import java.time.format.DateTimeFormatter

object DateUtilKt {
    /**
     * unix 时间戳格式化
     * @param [unixTimestamp] unix时间戳 (10位)
     * @return [String] 格式（yyyy-MM-dd HH:mm）
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
     * @return [LocalDate] 格式（yyyy-MM-dd）
     */
    @JvmStatic
    fun daysCalculation(date: LocalDate, days: Int): LocalDate {
        return date.plusDays(days.toLong())
    }

    /**
     * 日期计算（周）
     * @param [date] 日期
     * @param [weeks] 周
     * @return [LocalDate] 格式（yyyy-MM-dd）
     */
    @JvmStatic
    fun weeksCalculation(date: LocalDate, weeks: Int): LocalDate {
        return date.plusWeeks(weeks.toLong())
    }

    /**
     * 日期计算（月）
     * @param [date] 日期
     * @param [months] 月
     * @return [LocalDate] 格式（yyyy-MM-dd）
     */
    @JvmStatic
    fun monthsCalculation(date: LocalDate, months: Int): LocalDate {
        return date.plusMonths(months.toLong())
    }

    /**
     * 日期计算（年）
     * @param [date] 日期
     * @param [years] 年
     * @return [LocalDate] 格式（yyyy-MM-dd）
     */
    @JvmStatic
    fun yearsCalculation(date: LocalDate, years: Int): LocalDate {
        return date.plusYears(years.toLong())
    }

    /**
     * 加一年
     * @param [date] 日期
     * @return [String] 格式（yyyy-MM-dd）
     */
    @JvmStatic
    fun makeUpForTheWholeYear(date: String): String {
        return "${Year.now()}-$date"
    }

    /**
     * 补全日期
     * @param [time] 时间，格式（HH:mm）
     * @return [String] 格式（yyyy-MM-dd HH:mm:ss）
     */
    @JvmStatic
    fun complementaryDate(time: String): String {
        val now = LocalDateTime.now()
        return "${now.year}-${now.monthValue}-${now.dayOfMonth} ${time}:00"
    }

}