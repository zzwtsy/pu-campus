package cn.zzwtsy.pu.data.command

import kotlinx.serialization.Serializable

@Serializable
class PrivateCommand(
    val login: String = "login",
    val deleteUser: String = "删除信息"
)