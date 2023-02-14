package cn.zzwtsy.pu.data.command

import kotlinx.serialization.Serializable

@Serializable
class PublicCommand(
    var commandPrefix: String = "#",
    var help: String = "help"
)