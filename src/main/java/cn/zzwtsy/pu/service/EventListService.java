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
    private final String getEventListSuccess = "success";
    private final String getEventMessageNode = "message";
    private final String getEventContentNode = "content";
    Api api;
    ObjectMapper mapper;

    public EventListService() {
        api = new Api();
        mapper = new ObjectMapper();
    }

    /**
     * 根据日期获取事件列表
     *
     * @param qqId qq号
     * @param date 日期
     * @return {@link String}
     */
    public String getCalendarEventList(String qqId, String date) {
        String response;
        JsonNode jsonNode;
        User user = new UserService().getUser(qqId);
        String oauthToken = user.getOauthToken();
        String oauthTokenSecret = user.getOauthTokenSecret();
        try {
            response = api.getCalendarEventList(date, oauthToken, oauthTokenSecret);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("获取活动列表失败", e);
            return "获取活动列表失败";
        }
        try {
            jsonNode = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return e.getMessage();
        }
        String eventMessage = jsonNode.get(getEventMessageNode).asText();
        if (!getEventListSuccess.equals(eventMessage)) {
            return eventMessage;
        }
        JsonNode contentNode = jsonNode.get(getEventContentNode);
        if (contentNode.isEmpty()) {
            return "暂无可报名活动";
        }
        return new JsonParse().eventListParse(contentNode);
    }

    /**
     * 获取待签到列表
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     */
    public String getUserCanSignInEventList(String oauthToken, String oauthTokenSecret) {
        String response;
        JsonNode jsonNode;
        try {
            response = api.getUserCanSignInEventList(oauthToken, oauthTokenSecret);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error(e);
            return "获取待签到列表时发生错误";
        }
        try {
            jsonNode = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return e.getMessage();
        }
        String eventMessage = jsonNode.get(getEventMessageNode).asText();
        if (!getEventListSuccess.equals(eventMessage)) {
            return eventMessage;
        }
        JsonNode contentNode = jsonNode.get(getEventContentNode);
        if (contentNode.isEmpty()) {
            return "暂无待签到活动";
        }
        return new JsonParse().eventListParse(contentNode);
    }
}
