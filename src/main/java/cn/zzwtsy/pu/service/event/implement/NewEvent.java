package cn.zzwtsy.pu.service.event.implement;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.api.Api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import net.mamoe.mirai.message.data.MessageChainBuilder;

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
            return new MessageChainBuilder()
                    .append("获取新活动列表失败：")
                    .append(e.getMessage())
                    .build();
        }
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(newEventList);
        } catch (JsonProcessingException e) {
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
        JsonNode contentNode = jsonNode.get(eventContentNode);
        //判断 content 内容 是否为空
        if (contentNode.isEmpty()) {
            return new MessageChainBuilder()
                    .append("当前暂无新活动")
                    .build();
        }
        return jsonNode;
    }
}
