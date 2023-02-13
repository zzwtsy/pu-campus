package cn.zzwtsy.pu.data

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

/**
 * 设置
 * @author admin
 * @date 2023/02/12
 * @constructor 创建[Setting]
 */
object Setting : AutoSavePluginConfig("setting") {
    var botId by value(0L)
    var adminId:List<Long> by value()
    var groupId by value(0L)
    var timedTask by value("0")
    var emailSuffix by value("")
}