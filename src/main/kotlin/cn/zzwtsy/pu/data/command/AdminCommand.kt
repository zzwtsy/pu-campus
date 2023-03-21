package cn.zzwtsy.pu.data.command

import kotlinx.serialization.Serializable

@Serializable
class AdminCommand(
    val adminDeleteUser: String = "删除用户",
    val addPublicToken: String = "添加tk",
    val timedTask: String = "定时任务",
    val showTask: String = "显示任务"
)