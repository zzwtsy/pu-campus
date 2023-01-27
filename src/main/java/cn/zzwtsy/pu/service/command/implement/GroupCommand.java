package cn.zzwtsy.pu.service.command.implement;

import cn.zzwtsy.pu.service.UserCreditService;
import cn.zzwtsy.pu.service.event.EventService;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

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
        if (message.startsWith(helpCommand)) {
            return new MessageChainBuilder()
                    .append("===群聊可使用命令===\n\n")
                    .append(groupHelpInfo())
                    .append("\n\n\n===私聊可使用命令===\n\n")
                    .append(privateHelpInfo())
                    .build();
        }
        if (!checkUserLogin(userQqId)) {
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus("你还没有登陆请先私聊机器人登陆PU校园账户"))
                    .build();
        }
        //根据日期获取活动列表
        if (message.startsWith(eventListCommand)) {
            int commandLength = 2;
            String[] strings = splitMessage(message);
            if (strings.length != commandLength) {
                return new MessageChainBuilder()
                        .append("命令格式错误")
                        .build();
            }
            MessageChain messageChain = eventService.getCalendarEventList(strings[1]);
            if (messageChain.isEmpty()) {
                return new MessageChainBuilder()
                        .append(new At(userQqId))
                        .append("\n\n")
                        .append("暂无可参加活动")
                        .build();
            }
            return messageChain;
        }
        //获取活动已结束未发放学分列表
        if (message.startsWith(queryUserEventEndUnissuedCreditListCommand)) {
            return eventService.getEventEndUnissuedCreditEvent();
        }
        //用户登录
        if (message.startsWith(loginCommand)) {
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus("请私聊机器人登录"))
                    .build();
        }
        //查询学分信息
        if (message.startsWith(queryUserCreditInfoCommand)) {
            if (!checkUserLogin(userQqId)) {
                return new MessageChainBuilder()
                        .append(new At(userQqId)
                                .plus("你还没有登陆，请私聊机器人登录PU校园"))
                        .build();
            }
            String userCreditInfoMessage = new UserCreditService().userCredit(userQqId);
            return new MessageChainBuilder()
                    .append(new At(userQqId)
                            .plus(userCreditInfoMessage))
                    .build();
        }
        //获取待签到列表
        if (message.startsWith(querySignInEventListCommand)) {
            return eventService.getUserCanSignInEventList();
        }
        //获取待签退列表
        if (message.startsWith(querySignOutEventListCommand)) {
            return eventService.getUserCanSignOutEventList();
        }
        return null;
    }

}
