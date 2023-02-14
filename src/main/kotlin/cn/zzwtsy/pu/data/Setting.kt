package cn.zzwtsy.pu.data

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

/**
 * 设置
 * @author admin
 * @date 2023/02/12
 * @constructor 创建[Setting]
 */
object Setting : AutoSavePluginConfig("setting") {
    @ValueDescription("机器人 qq 号")
    var botId by value(0L)

    @ValueDescription("管理员 qq 号，示例：[111111,2222222]")
    var adminId: List<Long> by value()

    @ValueDescription("qq 群号")
    var groupId by value(0L)

    @ValueDescription("""
        定时任务时间，24小时制，示例：01:01（每天凌晨1点1分执行任务）
        0 为关闭定时任务
    """)
    var timedTask by value("0")

    @ValueDescription("学校邮箱后缀：https://blog.yumdeb.top/tools/PuSchoolInfo.html")
    var emailSuffix by value("")
}