package cn.zzwtsy.pu.service.event.implement;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.api.Api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

/**
 * 获取当日可参加的新活动列表
 *
 * @author zzwtsy
 * @since 2023/01/27
 */
public class NewEvent extends AbstractEvent {
    public NewEvent(long userQqId) {
        super(userQqId);
    }

    /**
     * 获取响应内容
     *
     * @return {@link Object}
     */
    @Override
    protected Object getResponse() {
        api = new Api();
        String newEventList;
        try {
            newEventList = api.getNewEventList(oauthToken, oauthTokenSecret);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("获取新活动列表失败：", e);
            return "获取新活动列表失败：" + e.getMessage();
        }
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(newEventList);
        } catch (JsonProcessingException e) {
            return "发生错误：" + e.getMessage();
        }
        //获取 JSON 文件的 Message 字段内容
        String messageContent = jsonNode.get(eventMessageNode).asText();
        // 判断 Message 内容是否等于 success，否则返回 Message 内容
        if (!eventListSuccessWord.equals(messageContent)) {
            return "发生错误：" + messageContent;
        }
        //获取 content 字段内容
        return jsonNode.get(eventContentNode);
    }
}
