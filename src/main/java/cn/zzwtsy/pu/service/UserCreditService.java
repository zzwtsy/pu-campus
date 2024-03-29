package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.api.Api;
import cn.zzwtsy.pu.bean.UserBean;
import cn.zzwtsy.pu.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * 用户学分
 *
 * @author zzwtsy
 * @since 2022/12/26
 */
public class UserCreditService {
    private final String totalNodeName;
    private final String messageNodeName;
    private final String getUserCreditSuccess;
    private final String contentNodeName;
    private final String creditInfoSuccessWord;
    public String activeCredit;
    public String applyCredit;

    public UserCreditService() {
        contentNodeName = "content";
        getUserCreditSuccess = "success";
        messageNodeName = "message";
        totalNodeName = "total";
        creditInfoSuccessWord = "学分类型";
    }

    /**
     * 学分信息
     *
     * @param qqId qq号
     * @return {@link String}
     */
    public String userCredit(long qqId) {
        Api api = new Api();
        StringBuilder stringBuilder = new StringBuilder();
        String activeCreditResponse;
        String applyCreditResponse;
        UserBean userBean = new UserService().getUser(qqId);
        String oauthToken = userBean.getOauthToken();
        String oauthTokenSecret = userBean.getOauthTokenSecret();
        try {
            activeCreditResponse = api.getActiveCredit(oauthToken, oauthTokenSecret);
            applyCreditResponse = api.getApplyCredit(oauthToken, oauthTokenSecret);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("获取学分信息失败", e);
            return "获取学分信息失败：" + e.getMessage();
        }
        String activeCreditContentParse = contentParse(activeCreditResponse, "activeCredit");
        String applyCreditContentParse = contentParse(applyCreditResponse, "applyCredit");
        if (!activeCreditContentParse.startsWith(creditInfoSuccessWord)) {
            return activeCreditContentParse;
        }
        if (!applyCreditContentParse.startsWith(creditInfoSuccessWord)) {
            return applyCreditContentParse;
        }
        BigDecimal creditTotal = new BigDecimal(activeCredit).add(new BigDecimal(applyCredit));
        stringBuilder.append("\n")
                .append(activeCreditContentParse)
                .append(applyCreditContentParse)
                .append("学分总分：")
                .append(creditTotal);
        return stringBuilder.toString();
    }

    /**
     * 内容解析
     *
     * @param creditResponse 解析内容
     * @param creditType     学分类型
     * @return {@link String}
     */
    private String contentParse(String creditResponse, String creditType) {
        JsonNode jsonNode;
        try {
            jsonNode = JsonUtil.fromJson(creditResponse);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("处理学分 JSON 时发生异常:", e);
            return "处理学分 JSON 时发生异常：" + e.getMessage();
        }
        String messageContent = jsonNode.get(messageNodeName).asText();
        if (!getUserCreditSuccess.equals(messageContent)) {
            return messageContent;
        }
        JsonNode contentNode = jsonNode.get(contentNodeName);
        String name = contentNode.get("name").asText();
        String total = contentNode.get(totalNodeName).asText();
        String applyCreditStr = "applyCredit";
        if (applyCreditStr.equals(creditType)) {
            applyCredit = total;
        } else {
            activeCredit = total;
        }
        return "学分类型：" + name + "\n" + "分数：" + total + "\n";
    }
}
