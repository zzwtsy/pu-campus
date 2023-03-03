package cn.zzwtsy.pu.service.event.implement;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * 活动签到状态
 *
 * @author zzwtsy
 * @since 2023/01/27
 */
public class SignInStateEvent extends AbstractEvent {
    private final boolean signInType;

    /**
     * 登录状态事件
     *
     * @param userQqId   用户qq
     * @param signInType 签到类型 true：待签到，false：待签退
     */
    public SignInStateEvent(long userQqId, boolean signInType) {
        super(userQqId);
        this.signInType = signInType;
    }

    /**
     * 得到响应
     * 获取响应内容
     *
     * @return {@link Object}
     */
    @Override
    protected Object getResponse() {
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
            jsonNode = JsonUtil.fromJson(response);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return "发生错误：" + e.getMessage();
        }
        String eventMessage = jsonNode.get(EVENT_MESSAGE_NODE).asText();
        if (!EVENT_LIST_SUCCESS_WORD.equals(eventMessage)) {
            return eventMessage;
        }
        JsonNode contentNode = jsonNode.get(EVENT_CONTENT_NODE);
        if (contentNode.isEmpty()) {
            return emptyEventListMessage;
        }
        return contentNode;
    }
}
