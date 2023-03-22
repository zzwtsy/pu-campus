package cn.zzwtsy.pu.data

import cn.zzwtsy.pu.data.command.AdminCommand
import cn.zzwtsy.pu.data.command.GroupCommand
import cn.zzwtsy.pu.data.command.PrivateCommand
import cn.zzwtsy.pu.data.command.PublicCommand
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.ValueName
import net.mamoe.mirai.console.data.value

object Command : AutoSavePluginConfig("command") {
    @ValueDescription("群组命令")
    val group: GroupCommand by value()

    @ValueDescription("私聊命令")
    @ValueName("private")
    val privateX: PrivateCommand by value()

    @ValueDescription("一些通用配置")
    @ValueName("public")
    val publicX: PublicCommand by value()

    @ValueDescription("管理员命令（仅在私聊可用）")
    val admin: AdminCommand by value()
}