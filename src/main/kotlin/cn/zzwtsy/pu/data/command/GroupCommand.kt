package cn.zzwtsy.pu.data.command

import kotlinx.serialization.Serializable

@Serializable
class GroupCommand(
    var queryUserCreditInfo: String = "学分信息",
    var querySignInEventList: String = "签到",
    var querySignOutEventList: String = "签退",
    var queryActivityDetailById: String = "活动信息",
    var getCalendarEventList: String = "活动",
    var queryUserEventEndUnissuedCreditList: String = "未发放学分活动",
)