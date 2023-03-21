package cn.zzwtsy.pu.data.command

import kotlinx.serialization.Serializable

@Serializable
class PublicCommand(
    val commandPrefix: String = "#",
    val help: String = "help"
)