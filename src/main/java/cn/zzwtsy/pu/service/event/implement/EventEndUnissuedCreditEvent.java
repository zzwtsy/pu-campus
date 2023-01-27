package cn.zzwtsy.pu.service.event.implement;

import cn.zzwtsy.pu.PuCampus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

/**
 * 活动结束未发放学分
 *
 * @author zzwtsy
 * @since 2023/01/27
 */
public class EventEndUnissuedCreditEvent extends AbstractEvent {

    public EventEndUnissuedCreditEvent(long userQqId) {
        super(userQqId);
    }

    /**
     * 获取响应内容
     *
     * @return {@link Object}
     */
    @Override
    protected Object getResponse() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode;
        ArrayNode jsonArray = mapper.createArrayNode();
        String response;
        int count = 10;
        for (int i = 1; ; i++) {
            String page = String.valueOf(i);
            try {
                response = api.getUserEventEndUnissuedCreditList(String.valueOf(userQqId), String.valueOf(count), page, oauthToken, oauthTokenSecret);
            } catch (IOException e) {
                PuCampus.INSTANCE.getLogger().error("获取未发放学分列表失败：", e);
                return new MessageChainBuilder()
                        .append("获取未发放学分列表失败：")
                        .append(e.getMessage())
                        .build();
            }
            try {
                jsonNode = mapper.readTree(response);
                //判断 json 是否有 message 字段
                if (jsonNode.hasNonNull(eventMessageNode)) {
                    return new MessageChainBuilder()
                            .append(jsonNode.get(eventMessageNode).asText())
                            .build();
                }
                //合并活动列表
                jsonArray.addAll((ArrayNode) jsonNode);
            } catch (JsonProcessingException e) {
                PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
                return new MessageChainBuilder()
                        .append("发生错误：")
                        .append(e.getMessage())
                        .build();
            }
            //判断当前页面是否为最后一页
            if (jsonNode.size() < count) {
                break;
            }
        }
        return jsonArray;
    }

    /**
     * 内容解析器
     *
     * @param content 要解析的内容
     * @return {@link MessageChain}
     */
    @Override
    protected MessageChain contentParser(JsonNode content) {
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
        if (message.isEmpty()) {
            return new MessageChainBuilder()
                    .append("暂无未发放学分活动")
                    .build();
        } else {
            return new MessageChainBuilder()
                    .append(message)
                    .build();
        }
    }
}
