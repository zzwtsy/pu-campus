package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.api.Api;
import cn.zzwtsy.pu.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static cn.zzwtsy.pu.utils.DateUtil.formatUnixTimestamp;

/**
 * 活动列表
 *
 * @author zzwtsy
 * @since 2022/12/05
 */
public class EventListService {
    private final String getEventListSuccess = "success";
    private final String getEventMessageNode = "message";
    private final String getEventContentNode = "content";
    Api api;
    User user;
    ObjectMapper mapper;

    public EventListService() {
        api = new Api();
        user = new User();
        mapper = new ObjectMapper();
    }

    /**
     * 根据日期获取活动列表
     *
     * @param qqId qq号
     * @param date 日期（MM-dd）
     * @return {@link String}
     */
    public String getCalendarEventList(long qqId, String date) {
        String response;
        JsonNode jsonNode;
        user = new UserService().getUser(qqId);
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
        return eventListParse(contentNode);
    }

    /**
     * 获取待签到列表
     *
     * @param qqId qq号
     * @return {@link String}
     */
    public String getUserCanSignInEventList(long qqId) {
        String response;
        JsonNode jsonNode;
        user = new UserService().getUser(qqId);
        String oauthToken = user.getOauthToken();
        String oauthTokenSecret = user.getOauthTokenSecret();
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
        return eventListParse(contentNode);
    }

    /**
     * 获取新活动列表
     *
     * @param userQqId 用户qq号
     * @return {@link String}
     */
    public String getNewEventList(long userQqId) {
        String message;
        api = new Api();
        user = new UserService().getUser(userQqId);
        String newEventList = null;
        try {
            newEventList = api.getNewEventList(user.getOauthToken(), user.getOauthTokenSecret());
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("获取新活动列表失败", e);
        }
        message = newEventListContentParser(newEventList);
        return message;
    }

    /**
     * 新活动列表内容解析
     *
     * @param content 活动内容
     * @return {@link String}
     */
    private String newEventListContentParser(String content) {
        mapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(content);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error(e);
            return "解析活动列表时发生错误";
        }
        String messageContent = jsonNode.get(getEventMessageNode).asText();
        if (!getEventListSuccess.equals(messageContent)) {
            return jsonNode.get(getEventMessageNode).asText();
        }
        JsonNode contentNode = jsonNode.get(getEventContentNode);
        if (contentNode.isEmpty()) {
            return "当前暂无新活动";
        }
        return eventListParse(contentNode);
    }

    /**
     * 活动列表解析
     *
     * @param contentNode 活动内容
     * @return {@link String}
     */
    private String eventListParse(JsonNode contentNode) {
        String event;
        JsonNode tempNode;
        //获取当前时间戳（转换为10位）
        long nowTimestamp = System.currentTimeMillis() / 1000;
        StringBuilder eventList = new StringBuilder();
        int contentLength = contentNode.size();
        for (int i = 0; i < contentLength; i++) {
            tempNode = contentNode.get(i);
            //活动状态
            String eventStatus = tempNode.get("eventStatus").asText();
            //报名结束时间
            long eventRegistrationCloseTime = tempNode.get("deadline").asLong();
            //判断活动是否仍在进行和当前时间大于报名结束时间
            if (!"4".equals(eventStatus) || nowTimestamp > eventRegistrationCloseTime) {
                continue;
            }
            //活动标题
            String title = tempNode.get("title").asText();
            //活动开始时间
            String sTime = formatUnixTimestamp(tempNode.get("sTime").asLong());
            //活动结束时间
            String eTime = formatUnixTimestamp(tempNode.get("eTime").asLong());
            //报名开始时间
            String startline = formatUnixTimestamp(tempNode.get("startline").asLong());
            //报名结束时间
            String deadline = formatUnixTimestamp(eventRegistrationCloseTime);
            //剩余可参加人数
            String limitCount = tempNode.get("limitCount").asText();
            //活动地址
            String address = tempNode.get("address").asText();
            event = "活动名称：\n\t\t\t《" + title + "》\n"
                    + "活动地址：\n\t\t\t『" + address + "』\n"
                    + "活动开始时间：\n\t\t\t" + sTime + "\n"
                    + "活动结束时间：\n\t\t\t" + eTime + "\n"
                    + "报名开始时间：\n\t\t\t" + startline + "\n"
                    + "报名结束时间：\n\t\t\t" + deadline + "\n"
                    + "剩余可参加人数：" + limitCount + "\n"
                    + "============" + "\n";
            eventList.append(event);
        }
        String eventListToString = eventList.toString();
        //判断获取的活动列表是否为空
        if (eventListToString.isEmpty()) {
            return "暂无可报名活动";
        }
        return eventListToString;
    }
}
