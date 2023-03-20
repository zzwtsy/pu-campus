package cn.zzwtsy.pu.service.event.implement;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.utils.RegexUtil;
import cn.zzwtsy.pu.utils.DateUtilKt;
import cn.zzwtsy.pu.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalDate;

import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;

/**
 * 根据日期获取活动列表
 *
 * @author zzwtsy
 * @since 2023/01/27
 */
public class CalendarEvent extends AbstractEvent {
    private final String date;

    public CalendarEvent(long userQqId, String date) {
        super(userQqId);
        this.date = getEventListDate(date);
    }

    /**
     * 获取消息
     *
     * @return {@link String}
     */
    @Override
    public String getMessage() {
        if (!RegexUtil.checkDateFormat(date)) {
            return "日期格式错误";
        }
        if (checkUserLogin(userQqId)) {
            return "你还没有登陆请先私聊机器人登陆PU校园账户";
        }
        return super.getMessage();
    }

    /**
     * 得到响应
     * 获取响应内容
     *
     * @return {@link Object}
     */
    @Override
    protected Object getResponse() {
        String response;
        JsonNode jsonNode;
        try {
            response = api.getCalendarEventList(date, oauthToken, oauthTokenSecret);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("获取活动列表失败", e);
            return "获取活动列表失败：" + e.getMessage();
        }
        try {
            jsonNode = JsonUtil.fromJson(response);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return "发生错误：" + e.getMessage();
        }
        //获取 JSON 文件的 Message 字段内容
        String messageContent = jsonNode.get(EVENT_MESSAGE_NODE).asText();
        // 判断 Message 内容是否等于 success，否则返回 Message 内容
        if (!EVENT_LIST_SUCCESS_WORD.equals(messageContent)) {
            return "发生错误：" + messageContent;
        }
        //获取 content 字段内容
        return jsonNode.get(EVENT_CONTENT_NODE);
    }

    /**
     * 获取活动列表
     *
     * @param dateParameter 日期
     */
    private String getEventListDate(String dateParameter) {
        LocalDate now = LocalDate.now();
        return switch (dateParameter) {
            case "今日", "今天" -> DateUtilKt.daysCalculation(now, 0).toString();
            case "明日", "明天" -> DateUtilKt.daysCalculation(now, +1).toString();
            case "昨日", "昨天" -> DateUtilKt.daysCalculation(now, -1).toString();
            default -> DateUtilKt.makeUpForTheWholeYear(dateParameter);
        };
    }
}
