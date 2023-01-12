package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.api.Api;
import cn.zzwtsy.pu.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

import static cn.zzwtsy.pu.utils.DateUtil.formatUnixTimestamp;

/**
 * 活动列表
 *
 * @author zzwtsy
 * @since 2022/12/05
 */
public class EventListService {
    private final String eventListSuccessWord = "success";
    private final String eventMessageNode = "message";
    private final String eventContentNode = "content";
    Api api;
    User user;
    ObjectMapper mapper;

    public EventListService() {
        api = new Api();
        user = new User();
        mapper = new ObjectMapper();
    }

    /**
     * 获取活动已结束未发放学分列表
     *
     * @param userQqId 用户qq
     * @return {@link String}
     */
    public String getUserEventEndUnissuedCreditList(long userQqId) {
        //一次请求10个活动信息
        int count = 10;
        String response;
        JsonNode jsonNode;
        ArrayNode jsonArray = mapper.createArrayNode();
        //获取用户信息
        user = new UserService().getUser(userQqId);
        String userUid = user.getUid();
        String oauthToken = user.getOauthToken();
        String oauthTokenSecret = user.getOauthTokenSecret();
        for (int i = 1; ; i++) {
            try {
                response = api.getUserEventEndUnissuedCreditList(userUid, String.valueOf(count), String.valueOf(i), oauthToken, oauthTokenSecret);
            } catch (IOException e) {
                PuCampus.INSTANCE.getLogger().error("获取未发放学分列表失败", e);
                return "获取未发放学分列表失败";
            }
            try {
                jsonNode = mapper.readTree(response);
                //判断 json 是否有 message 字段
                if (jsonNode.hasNonNull(eventMessageNode)) {
                    return jsonNode.get(eventMessageNode).asText();
                }
                //合并活动列表
                jsonArray.addAll((ArrayNode) jsonNode);
            } catch (JsonProcessingException e) {
                return e.getMessage();
            }
            //判断当前页面是否为最后一页
            if (jsonNode.size() < count) {
                break;
            }
        }
        if (jsonArray.size() == 0) {
            return "暂无未发放学分活动";
        }
        return userEventEndUnissuedCreditListParser(jsonArray);
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
        //获取 JSON 文件的 Message 字段内容
        String messageContent = jsonNode.get(eventMessageNode).asText();
        // 判断 Message 内容是否等于 success，否则返回 Message 内容
        if (!eventListSuccessWord.equals(messageContent)) {
            return "发生错误：" + messageContent;
        }
        //获取 content 字段内容
        JsonNode contentNode = jsonNode.get(eventContentNode);
        //判断 content 内容 是否为空
        if (contentNode.isEmpty()) {
            return "暂无可报名活动";
        }
        String eventListParse = eventListParse(contentNode);
        //判断获取的活动列表是否为空
        return eventListParse.isEmpty() ? "暂无可报名活动" : eventListParse;
    }

    /**
     * 获取待签到列表
     *
     * @param userId qq号
     * @return {@link String}
     */
    public String getUserCanSignInEventList(long userId) {
        return userCanSignInEventList(userId, true);
    }

    /**
     * 获取待签退列表
     *
     * @param userId qq号
     * @return {@link String}
     */
    public String getUserCanSignOutEventList(long userId) {
        return userCanSignInEventList(userId, false);
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
     * 用户签到活动列表
     *
     * @param userId     用户id
     * @param signInType 签到类型 true：待签到，false：待签退
     * @return {@link String}
     */
    private String userCanSignInEventList(long userId, boolean signInType) {
        String response;
        String errorMessage;
        String emptyEventListMessage;
        if (signInType) {
            errorMessage = "获取待签到列表时发生错误";
            emptyEventListMessage = "暂无待签到活动";
        } else {
            errorMessage = "获取待签退列表时发生错误";
            emptyEventListMessage = "暂无待签退活动";
        }
        JsonNode jsonNode;
        user = new UserService().getUser(userId);
        String oauthToken = user.getOauthToken();
        String oauthTokenSecret = user.getOauthTokenSecret();
        try {
            if (signInType) {
                response = api.getUserCanSignInEventList(oauthToken, oauthTokenSecret);
            } else {
                response = api.getUserCanSingOutEventList(oauthToken, oauthTokenSecret);
            }
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error(e);
            return errorMessage;
        }
        try {
            jsonNode = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return e.getMessage();
        }
        String eventMessage = jsonNode.get(eventMessageNode).asText();
        if (!eventListSuccessWord.equals(eventMessage)) {
            return eventMessage;
        }
        JsonNode contentNode = jsonNode.get(eventContentNode);
        if (contentNode.isEmpty()) {
            return emptyEventListMessage;
        }
        return eventListParse(contentNode);
    }

    /**
     * 解析活动已结束未发放学分列表
     *
     * @param content 内容
     * @return {@link String}
     */
    private String userEventEndUnissuedCreditListParser(JsonNode content) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = content.size();
        for (int i = 0; i < size; i++) {
            //活动已结束未发放学分活动的 can_evaluate 值为 1
            if (content.get(i).get("can_evaluate").asInt() != 1) {
                continue;
            }
            stringBuilder.append("《").append(content.get(i).get("title").asText()).append("》").append("\n");
        }
        String message = stringBuilder.toString();
        //判断是否存在未发放学分活动
        return message.isEmpty() ? "暂无未发放学分活动" : message;
    }

    /**
     * 解析新活动列表内容
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
        //获取 JSON 文件的 Message 字段内容
        String messageContent = jsonNode.get(eventMessageNode).asText();
        // 判断 Message 内容是否等于 success，否则返回 Message 内容
        if (!eventListSuccessWord.equals(messageContent)) {
            return "发生错误：" + messageContent;
        }
        //获取 content 字段内容
        JsonNode contentNode = jsonNode.get(eventContentNode);
        //判断 content 内容 是否为空
        if (contentNode.isEmpty()) {
            return "当前暂无新活动";
        }
        return eventListParse(contentNode);
    }

    /**
     * 解析活动列表
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
            //判断活动是否仍在进行和当前时间是否大于报名结束时间，是则跳过本次循环
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
        return eventList.toString();
    }
}
