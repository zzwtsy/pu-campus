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

import static cn.zzwtsy.pu.api.ApiUrl.CALENDAR_EVENT_LIST_URL;
import static cn.zzwtsy.pu.utils.DateUtil.formatUnixTimestamp;

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
        if (!getEventListSuccess.equals(jsonNode.get(getEventMessageNode).asText())) {
            return jsonNode.get(getEventMessageNode).asText();
        }
        if (jsonNode.get(getEventContentNode).size() == 0) {
            return "获取活动列表失败";
        }
        String contentParse = contentParse(jsonNode.get(getEventContentNode));
        return "".equals(contentParse) ? "暂无可报名活动" : contentParse;
    }


    /**
     * 内容解析
     *
     * @param contentNode 内容节点
     * @return {@link String}
     */
    private String contentParse(JsonNode contentNode) {
        String message;
        JsonNode tempNode;
        //获取当前时间戳（转换为10位）
        long nowTimestamp = System.currentTimeMillis() / 1000;
        StringBuilder messagesList = new StringBuilder();
        for (int i = 0; i < contentNode.size(); i++) {
            tempNode = contentNode.get(i);
            String eventStatus = tempNode.get("eventStatus").asText();
            long eventStartline = tempNode.get("deadline").asLong();
            if ("4".equals(eventStatus) && nowTimestamp < eventStartline) {
                //活动标题
                String title = tempNode.get("title").asText();
                //活动开始时间
                String sTime = formatUnixTimestamp(tempNode.get("sTime").asLong());
                //活动结束时间
                String eTime = formatUnixTimestamp(tempNode.get("eTime").asLong());
                //报名开始时间
                String startline = formatUnixTimestamp(eventStartline);
                //报名结束时间
                String deadline = formatUnixTimestamp(tempNode.get("deadline").asLong());
                //剩余可参加人数
                String limitCount = tempNode.get("limitCount").asText();
                //活动地址
                String address = tempNode.get("address").asText();
                message = "活动名称：\n\t\t\t《" + title + "》\n"
                        + "活动地址：\n\t\t\t『" + address + "』\n"
                        + "活动开始时间：\n\t\t\t" + sTime + "\n"
                        + "活动结束时间：\n\t\t\t" + eTime + "\n"
                        + "报名开始时间：\n\t\t\t" + startline + "\n"
                        + "报名结束时间：\n\t\t\t" + deadline + "\n"
                        + "剩余可参加人数：" + limitCount + "\n"
                        + "============" + "\n";
                messagesList.append(message);
            }
        }
        return messagesList.toString();
    }
}
