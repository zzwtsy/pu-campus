package cn.zzwtsy.pu.service.event.implement;

import cn.zzwtsy.pu.PuCampus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import static cn.zzwtsy.pu.tools.Tools.checkUserLogin;
import static cn.zzwtsy.pu.utils.DateUtil.addYear;
import static cn.zzwtsy.pu.utils.DateUtil.checkDateFormat;
import static cn.zzwtsy.pu.utils.DateUtil.dateCalculate;

/**
 * 根据日期获取活动列表
 *
 * @author zzwtsy
 * @since 2023/01/27
 */
public class CalendarEvent extends AbstractEvent{
    private final String date;
    public CalendarEvent(long userQqId,String date) {
        super(userQqId);
        this.date = getEventListDate(date);
    }

    /**
     * 获取消息
     *
     * @return {@link MessageChain}
     */
    @Override
    public MessageChain getMessage() {
        if (!checkDateFormat(date)) {
            return new MessageChainBuilder()
                    .append("日期格式错误")
                    .build();
        }
        if (!checkUserLogin(userQqId)) {
            return new MessageChainBuilder()
                    .append("你还没有登陆请先私聊机器人登陆PU校园账户")
                    .build();
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
            return new MessageChainBuilder()
                    .append("获取活动列表失败：")
                    .append(e.getMessage())
                    .build();
        }
        try {
            jsonNode = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return new MessageChainBuilder()
                    .append("发生错误：")
                    .append(e.getMessage())
                    .build();
        }
        //获取 JSON 文件的 Message 字段内容
        String messageContent = jsonNode.get(eventMessageNode).asText();
        // 判断 Message 内容是否等于 success，否则返回 Message 内容
        if (!eventListSuccessWord.equals(messageContent)) {
            return new MessageChainBuilder()
                    .append("发生错误：")
                    .append(messageContent)
                    .build();
        }
        //获取 content 字段内容
        return jsonNode.get(eventContentNode);
    }

    /**
     * 内容解析器
     *
     * @param content 要解析的内容
     * @return {@link MessageChain}
     */
    @Override
    protected MessageChain contentParser(JsonNode content) {
        return super.contentParser(content);
    }

    /**
     * 获取活动列表
     *
     * @param dateParameter 日期
     */
    private String getEventListDate(String dateParameter) {
        String date;
        switch (dateParameter) {
            case "今日":
            case "今天":
                date = dateCalculate(0);
                break;
            case "明日":
            case "明天":
                date = dateCalculate(+1);
                break;
            case "昨日":
            case "昨天":
                date = dateCalculate(-1);
                break;
            default:
                date = addYear(dateParameter);
                break;
        }
        return date;
    }
}
