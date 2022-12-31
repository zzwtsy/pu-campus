package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.api.Api;
import cn.zzwtsy.pu.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

/**
 * 用户学分
 *
 * @author zzwtsy
 * @since 2022/12/26
 */
public class UserCreditService {
    private final HashMap<String, Double> creditTotalMap = new HashMap<>(2);

    /**
     * 学分信息
     *
     * @param qqId qq号
     * @return {@link String}
     */
    public String userCredit(String qqId) {
        Api api = new Api();
        String getCreditInfoSuccess = "学分类型";
        StringBuilder stringBuilder = new StringBuilder();
        String activeCreditResponse;
        String applyCreditResponse;
        User user = new UserService().getUser(qqId);
        String oauthToken = user.getOauthToken();
        String oauthTokenSecret = user.getOauthTokenSecret();
        try {
            activeCreditResponse = api.getActiveCredit(oauthToken, oauthTokenSecret);
            applyCreditResponse = api.getApplyCredit(oauthToken, oauthTokenSecret);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("获取学分信息失败");
            return "获取学分信息失败：" + e.getMessage();
        }
        String activeCreditContentParse = contentParse(activeCreditResponse, "activeCredit");
        String applyCreditContentParse = contentParse(applyCreditResponse, "applyCredit");
        if (!activeCreditContentParse.startsWith(getCreditInfoSuccess)) {
            return activeCreditContentParse;
        }
        if (!applyCreditContentParse.startsWith(getCreditInfoSuccess)) {
            return activeCreditContentParse;
        }
        Double creditTotal = creditTotalMap.get("activeCredit") + creditTotalMap.get("applyCredit");
        stringBuilder.append("\n")
                .append(activeCreditContentParse)
                .append(applyCreditContentParse)
                .append("学分总分：")
                .append(creditTotal);
        return stringBuilder.toString();
    }

    private String contentParse(String creditResponse, String creditType) {
        String totalNodeName = "total";
        String messageNodeName = "message";
        String getUserCreditSuccess = "success";
        String contentNodeName = "content";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(creditResponse);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("", e);
            return "处理学分 JSON 时发生异常：" + e.getMessage();
        }
        String messageContent = jsonNode.get(messageNodeName).asText();
        if (!getUserCreditSuccess.equals(messageContent)) {
            return messageContent;
        }
        JsonNode contentNode = jsonNode.get(contentNodeName);
        String name = contentNode.get("name").asText();
        double total = contentNode.get(totalNodeName).asDouble();
        creditTotalMap.put(creditType, total);
        return "学分类型：" + name + "\n" + "分数：" + total + "\n";
    }
}
