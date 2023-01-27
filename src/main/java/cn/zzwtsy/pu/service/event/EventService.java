package cn.zzwtsy.pu.service.event;

import cn.zzwtsy.pu.service.event.implement.CalendarEvent;
import cn.zzwtsy.pu.service.event.implement.EventEndUnissuedCreditEvent;
import cn.zzwtsy.pu.service.event.implement.NewEvent;
import cn.zzwtsy.pu.service.event.implement.SignInStateEvent;
import net.mamoe.mirai.message.data.MessageChain;

/**
 * 事件
 *
 * @author zzwtsy
 * @since 2023/01/27
 */
public class EventService {
    private final long userQqId;

    public EventService(long userQqId) {
        this.userQqId = userQqId;
    }

    /**
     * 根据日期获取活动列表
     *
     * @param date 日期
     * @return {@link MessageChain}
     */
    public MessageChain getCalendarEventList(String date) {
        return new CalendarEvent(userQqId, date).getMessage();
    }

    public MessageChain getEventEndUnissuedCreditEvent() {
        return new EventEndUnissuedCreditEvent(userQqId).getMessage();
    }

    /**
     * 获取待签到列表
     *
     * @return {@link String}
     */
    public MessageChain getUserCanSignInEventList() {
        return new SignInStateEvent(userQqId, true).getMessage();
    }

    /**
     * 获取待签退列表
     *
     * @return {@link String}
     */
    public MessageChain getUserCanSignOutEventList() {
        return new SignInStateEvent(userQqId, false).getMessage();
    }

    /**
     * 获取当日可参加的新活动列表
     *
     * @return {@link MessageChain}
     */
    public MessageChain getNewEventList(){
        return new NewEvent(userQqId).getMessage();
    }
}
