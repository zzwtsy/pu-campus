package cn.zzwtsy.pu.data.command

import kotlinx.serialization.Serializable

@Serializable
class PrivateCommand(
    var login: String = "login",
    var deleteUser: String = "删除信息"
)