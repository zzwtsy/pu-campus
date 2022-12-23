package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.User;
import cn.zzwtsy.pu.tools.MyHeaders;
import cn.zzwtsy.pu.tools.MyRequestBody;
import cn.zzwtsy.pu.utils.HttpHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Date;

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
     */
    public String getCalendarEventList(String qqId, Date date) {
        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        JsonNode jsonNode;
        User user = new UserService().getUser(qqId);
        String oauthToken = user.getOauthToken();
        String oauthTokenSecret = user.getOauthTokenSecret();
        try {
            response = HttpHelper.sendPost(CALENDAR_EVENT_LIST_URL,
                    MyHeaders.tokenHeaders(oauthToken, oauthTokenSecret),
                    MyRequestBody.calendarEventListBody(String.valueOf(date), oauthToken, oauthTokenSecret));
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
     * @return {@link String}
     */
    private String contentParse(JsonNode contentNode) {
        return null;
    }
}
