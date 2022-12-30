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
 * 新事件列表
 *
 * @author zzwtsy
 * @since 2022/12/30
 */
public class NewEventListService {

    public String getNewEventList(String userQqId) {
        String message;
        Api api = new Api();
        User user = new UserService().getUser(userQqId);
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
     * 新事件列表内容解析
     *
     * @param content 内容
     * @return {@link String}
     */
    public String newEventListContentParser(String content) {
        String getNewEventListSuccess = "success";
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(content);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error(e);
            return "解析活动列表时发生错误";
        }
        String messageContent = jsonNode.get("message").asText();
        if (!getNewEventListSuccess.equals(messageContent)) {
            return jsonNode.get("message").asText();
        }
        JsonNode contentNode = jsonNode.get("content");
        if (contentNode.isEmpty()) {
            return "当前暂无新活动";
        }
        return new JsonParse().eventListParse(contentNode);
    }
}
