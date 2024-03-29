package cn.zzwtsy.pu.service.command.implement;

import cn.zzwtsy.pu.service.UserCreditService;
import cn.zzwtsy.pu.service.event.EventService;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import static cn.zzwtsy.pu.tools.CommandConsts.EVENT_LIST_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.HELP_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.LOGIN_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.QUERY_SIGN_IN_EVENT_LIST_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.QUERY_SIGN_OUT_EVENT_LIST_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.QUERY_USER_CREDIT_INFO_COMMAND;
import static cn.zzwtsy.pu.tools.CommandConsts.QUERY_USER_EVENT_END_UNISSUED_CREDIT_LIST_COMMAND;
import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;
import static cn.zzwtsy.pu.tools.Tools.splitMessage;

/**
 * 群组
 *
 * @author zzwtsy
 * @since 2023/01/26
 */
public class GroupCommand extends AbstractCommand {

    @Override
    public MessageChain processingCommand(String message, long userQqId) {
        EventService eventService = new EventService(userQqId);
        //获取帮助信息
        if (message.startsWith(HELP_COMMAND)) {
            return new MessageChainBuilder()
                    .append("===群聊可使用命令===\n\n")
                    .append(groupHelpInfo())
                    .append("\n\n\n===私聊可使用命令===\n\n")
                    .append(privateHelpInfo())
                    .build();
        }
        if (checkUserLogin(userQqId)) {
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus("你还没有登陆请先私聊机器人登陆PU校园账户"))
                    .build();
        }
        //根据日期获取活动列表
        if (message.startsWith(EVENT_LIST_COMMAND)) {
            int commandLength = 2;
            String[] strings = splitMessage(message);
            if (strings.length != commandLength) {
                return new MessageChainBuilder()
                        .append("命令格式错误")
                        .build();
            }
            String str = eventService.getCalendarEventList(strings[1]);
            if (str.isEmpty()) {
                return new MessageChainBuilder()
                        .append(new At(userQqId))
                        .append("\n\n")
                        .append("暂无可参加活动")
                        .build();
            }
            return new MessageChainBuilder()
                    .append(new At(userQqId))
                    .append("\n\n")
                    .append(str)
                    .build();
        }
        //获取活动已结束未发放学分列表
        if (message.startsWith(QUERY_USER_EVENT_END_UNISSUED_CREDIT_LIST_COMMAND)) {
            String str = eventService.getEventEndUnissuedCreditEvent();
            return new MessageChainBuilder()
                    .append(new At(userQqId))
                    .append("\n以下活动还未发放学分")
                    .append("\n\n")
                    .append(str)
                    .build();
        }
        //用户登录
        if (message.startsWith(LOGIN_COMMAND)) {
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus("请私聊机器人登录"))
                    .build();
        }
        //查询学分信息
        if (message.startsWith(QUERY_USER_CREDIT_INFO_COMMAND)) {
            if (checkUserLogin(userQqId)) {
                return new MessageChainBuilder()
                        .append(new At(userQqId)
                                .plus("你还没有登陆，请私聊机器人登录PU校园"))
                        .build();
            }
            String userCreditInfoMessage = new UserCreditService().userCredit(userQqId);
            return new MessageChainBuilder()
                    .append(new At(userQqId))
                    .append("\n")
                    .append(userCreditInfoMessage)
                    .build();
        }
        //获取待签到列表
        if (message.startsWith(QUERY_SIGN_IN_EVENT_LIST_COMMAND)) {
            String str = eventService.getUserCanSignInEventList();
            return new MessageChainBuilder()
                    .append(new At(userQqId))
                    .append("\n")
                    .append(str)
                    .build();
        }
        //获取待签退列表
        if (message.startsWith(QUERY_SIGN_OUT_EVENT_LIST_COMMAND)) {
            String str = eventService.getUserCanSignOutEventList();
            return new MessageChainBuilder()
                    .append(new At(userQqId))
                    .append("\n")
                    .append(str)
                    .build();
        }
        return new MessageChainBuilder().build();
    }

}
