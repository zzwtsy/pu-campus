package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.api.Api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * 获取令牌
 *
 * @author zzwtsy
 * @since 2022/11/30
 */
public class LoginService {

    /**
     * @param userName 用户名
     * @param password 用户密码
     * @return String
     */
    public String getUserToken(long qqId, String userName, String password) {
        String userTokenSuccessWord = "success";
        ObjectMapper mapper = new ObjectMapper();
        String response;
        try {
            response = new Api().getLoginInfo(userName, password);
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("发送登录请求失败", e);
            return "发送登录请求失败:" + e.getMessage();
        }
        JsonNode jsonNode;
        try {
            jsonNode = mapper.readTree(response);
        } catch (JsonProcessingException e) {
            PuCampus.INSTANCE.getLogger().error("JsonProcessingException", e);
            return e.getMessage();
        }
        JsonNode contentNode = jsonNode.get("content");
        String message = jsonNode.get("message").asText();
        if (!userTokenSuccessWord.equals(message)) {
            return message;
        }
        String uid = contentNode.get("user_info").get("uid").asText();
        String oauthToken = contentNode.get("oauth_token").asText();
        String oauthTokenSecret = contentNode.get("oauth_token_secret").asText();
        if (oauthToken == null || oauthTokenSecret == null || oauthToken.isEmpty() || oauthTokenSecret.isEmpty()) {
            return "获取用户Token失败";
        }
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
