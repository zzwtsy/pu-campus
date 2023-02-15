package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.api.Api;
import cn.zzwtsy.pu.utils.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

/**
 * 获取令牌
 *
 * @author zzwtsy
 * @since 2022/11/30
 */
public class LoginService {

    /**
     * 获取用户令牌
     *
     * @param userName 用户名
     * @param password 用户密码
     * @param qqId     qq
     * @return String
     */
    public String getUserToken(long qqId, String userName, String password) {
        String response;
        try {
            response = new Api().getLoginInfo(userName, password);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("发送登录请求失败", e);
            return "发送登录请求失败:" + e.getMessage();
        }
        JsonNode jsonNode;
        try {
            jsonNode = JsonUtil.fromJson(response);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return e.getMessage();
        }
        JsonNode contentNode = jsonNode.get("content");
        String message = jsonNode.get("message").asText();
        String userTokenSuccessWord = "success";
        if (!userTokenSuccessWord.equals(message)) {
            return message;
        }
        String uid = contentNode.get("user_info").get("uid").asText();
        String oauthToken = contentNode.get("oauth_token").asText();
        String oauthTokenSecret = contentNode.get("oauth_token_secret").asText();
        if (oauthToken == null || oauthTokenSecret == null || oauthToken.isEmpty() || oauthTokenSecret.isEmpty()) {
            return "获取用户Token失败";
        }
        return setUserInfo(qqId, uid, oauthToken, oauthTokenSecret);
    }

    /**
     * 获取用户uid
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     */
    public String getUserUid(long qqId, String oauthToken, String oauthTokenSecret) {
        String userInfo;
        JsonNode jsonNode;
        try {
            userInfo = new Api().getUserInfo(oauthToken, oauthTokenSecret);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("获取用户信息失败：", e);
            return "添加用户 Token 失败";
        }
        try {
            jsonNode = JsonUtil.fromJson(userInfo);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return e.getMessage();
        }
        String message = jsonNode.get("message").asText();
        String getUserInfoFailed = "授权失败";
        if (getUserInfoFailed.equals(message)) {
            return message;
        }
        String uid = jsonNode.get("content").get("user_info").get("uid").asText();
        return setUserInfo(qqId, uid, oauthToken, oauthTokenSecret);
    }

    /**
     * 设置用户信息
     *
     * @param qqId             qq
     * @param uid              uid
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     */
    private String setUserInfo(long qqId, String uid, String oauthToken, String oauthTokenSecret) {
        UserService userService = new UserService();
        if (userService.getUser(qqId) != null) {
            //用户已存在，更新用户信息
            int updateUserStatus = userService.updateUser(qqId, uid, oauthToken, oauthTokenSecret);
            if (updateUserStatus <= 0) {
                return "登录失败:更新用户Token失败";
            }
            return "更新用户信息成功";
        }
        //用户不存在，添加用户
        int addUserStatus = userService.addUser(qqId, uid, oauthToken, oauthTokenSecret);
        if (addUserStatus <= 0) {
            return "登录失败:添加用户Token失败";
        }
        return "登陆成功";
    }
}
