package cn.zzwtsy.pu.data.command

import kotlinx.serialization.Serializable

@Serializable
class GroupCommand(
    val queryUserCreditInfo: String = "学分信息",
    val querySignInEventList: String = "签到",
    val querySignOutEventList: String = "签退",
//    var queryActivityDetailById: String = "活动信息",
    val getCalendarEventList: String = "活动",
    val queryUserEventEndUnissuedCreditList: String = "未发放学分活动",
)