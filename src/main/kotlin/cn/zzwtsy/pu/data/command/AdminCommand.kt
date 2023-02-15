package cn.zzwtsy.pu.data.command

import kotlinx.serialization.Serializable

@Serializable
class AdminCommand(
    var adminDeleteUser: String = "删除用户",
    var addPublicToken: String = "添加tk",
    var timedTask: String = "定时任务",
    var showTask: String = "显示任务"
)