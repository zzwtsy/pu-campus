package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.api.Api;
import cn.zzwtsy.pu.bean.User;
import cn.zzwtsy.pu.tools.JsonParse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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
        String getEventListSuccess = "success";
        String getEventMessageNode = "message";
        String getEventContentNode = "content";
        ObjectMapper objectMapper = new ObjectMapper();
        String response;
        JsonNode jsonNode;
        User user = new UserService().getUser(qqId);
        String oauthToken = user.getOauthToken();
        String oauthTokenSecret = user.getOauthTokenSecret();
        try {
            response = new Api().getCalendarEventList(date, oauthToken, oauthTokenSecret);
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
        if (!getEventListSuccess.equals(jsonNode.get(getEventMessageNode).asText())) {
            return jsonNode.get(getEventMessageNode).asText();
        }
        if (jsonNode.get(getEventContentNode).size() == 0) {
            return "获取活动列表失败";
        }
        String contentParse = new JsonParse().eventListParse(jsonNode.get(getEventContentNode));
        return "".equals(contentParse) ? "暂无可报名活动" : contentParse;
    }
}
