package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.User;
import cn.zzwtsy.pu.tools.FormatTime;
import cn.zzwtsy.pu.tools.MyHeaders;
import cn.zzwtsy.pu.tools.MyRequestBody;
import cn.zzwtsy.pu.utils.HttpHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.regex.Pattern;

import static cn.zzwtsy.pu.api.ApiUrl.CALENDAR_EVENT_LIST_URL;

/**
 * 事件列表
 *
 * @author zzwtsy
 * @since 2022/12/05
 */
public class EventListService {
    /**
     * 根据日期获取事件列表
     *
     * @param qqId qq号
     * @param date 日期
     * @return {@link String}
     */
    public String getCalendarEventList(String qqId, String date) {
        if (!checkDateFormat(date)) {
            return "日期格式错误";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        JsonNode jsonNode;
        User user = new UserService().getUser(qqId);
        String oauthToken = user.getOauthToken();
        String oauthTokenSecret = user.getOauthTokenSecret();
        try {
            response = HttpHelper.sendPost(CALENDAR_EVENT_LIST_URL,
                    MyHeaders.tokenHeaders(oauthToken, oauthTokenSecret),
                    MyRequestBody.calendarEventListBody(date, oauthToken, oauthTokenSecret));
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("获取活动列表失败", e);
            return "获取活动列表失败";
        }
        try {
            jsonNode = objectMapper.readTree(response);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return e.getMessage();
        }
        if (!"success".equals(jsonNode.get("message").asText())) {
            return jsonNode.get("message").asText();
        }
        return contentParse(jsonNode.get("content"));
    }


    /**
     * 内容解析
     *
     * @param contentNode 内容节点
     * @return {@link String}
     */
    private String contentParse(JsonNode contentNode) {
        String message = null;
        for (int i = 0; i < contentNode.size(); i++) {
            JsonNode tempNode = contentNode.get(i);
            String eventStatus = tempNode.get("eventStatus").asText();
            if ("4".equals(eventStatus)) {
                //活动标题
                String title = tempNode.get("title").asText();
                //活动开始时间
                String sTime = FormatTime.formatUnixTimestamp(tempNode.get("sTime").asLong());
                //活动结束时间
                String eTime = FormatTime.formatUnixTimestamp(tempNode.get("eTime").asLong());
                //报名开始时间
                String startline = FormatTime.formatUnixTimestamp(tempNode.get("startline").asLong());
                //报名结束时间
                String deadline = FormatTime.formatUnixTimestamp(tempNode.get("deadline").asLong());
                //剩余可参加人数
                String limitCount = tempNode.get("limitCount").asText();
                //活动地址
                String address = tempNode.get("address").asText();
                message = "活动名称:" + title + "\n"
                        + "活动地址:" + address + "\n"
                        + "活动开始时间:" + sTime + "\n"
                        + "活动结束时间:" + eTime + "\n"
                        + "报名开始时间:" + startline + "\n"
                        + "报名结束时间" + deadline + "\n"
                        + "剩余可参加人数:" + limitCount + "\n"
                        + "============";
            }
        }
        return message;
    }

    private boolean checkDateFormat(String date) {
        String dateFormat = "^\\d{1,4}(-)(1[0-2]|0?[1-9])\\1(0?[1-9]|[1-2]\\d|30|31)$";
        return Pattern.matches(dateFormat, date);
    }
}
