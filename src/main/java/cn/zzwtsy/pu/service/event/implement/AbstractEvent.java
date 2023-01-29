package cn.zzwtsy.pu.service.event.implement;

import cn.zzwtsy.pu.api.Api;
import cn.zzwtsy.pu.bean.UserBean;
import cn.zzwtsy.pu.service.UserService;
import cn.zzwtsy.pu.service.event.Event;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;

import static cn.zzwtsy.pu.utils.DateUtil.formatUnixTimestamp;

/**
 * 抽象事件
 *
 * @author zzwtsy
 * @since 2023/01/27
 */
public abstract class AbstractEvent implements Event {
    protected long userQqId;
    protected Api api;
    protected ObjectMapper mapper;
    protected String oauthToken;
    protected String oauthTokenSecret;
    protected final String eventListSuccessWord = "success";
    protected final String eventMessageNode = "message";
    protected final String eventContentNode = "content";

    public AbstractEvent(long userQqId){
        this.userQqId = userQqId;
        api = new Api();
        mapper = new ObjectMapper();
        UserBean userBean = new UserService().getUser(userQqId);
        oauthToken = userBean.getOauthToken();
        oauthTokenSecret = userBean.getOauthTokenSecret();
    }

    /**
     * 获取消息
     *
     * @return {@link MessageChain}
     */
    @Override
    public MessageChain getMessage() {
        Object response = getResponse();
        if (response instanceof JsonNode content){
            return contentParser(content);
        }
        if (response instanceof MessageChain messageChain){
            return messageChain;
        }
        return new MessageChainBuilder()
                .append("为什么代码会走到这里")
                .build();
    }

    /**
     * 获取响应内容
     *
     * @return {@link Object}
     */
    protected abstract Object getResponse();

    /**
     * 内容解析器
     *
     * @param content 要解析的内容
     * @return {@link String}
     */
    protected MessageChain contentParser(JsonNode content){
        //判断 content 内容 是否为空
        if (content.isEmpty()) {
            return new MessageChainBuilder()
                    .append("暂无可报名活动")
                    .build();
        }
        //获取当前时间戳（转换为秒）
        long nowTimestamp = System.currentTimeMillis() / 1000;
        StringBuilder eventList = new StringBuilder();
        int contentLength = content.size();
        for (int i = 0; i < contentLength; i++) {
            JsonNode tempNode = content.get(i);
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
            String event = """
                    活动名称：
                        《 %s 》
                    活动地址：
                        『%s』
                    活动开始时间：
                        %s
                    活动结束时间：
                        %s
                    报名开始时间：
                        %s
                    报名结束时间：
                        %s
                    剩余可参加人数：
                        %s
                    ============
                    """;
            String format = String.format(event, title, address, sTime, eTime, startline, deadline, limitCount);
            eventList.append(format);
        }
        return new MessageChainBuilder()
                .append(new At(userQqId))
                .append("\n")
                .append(eventList)
                .build();
    }

}
