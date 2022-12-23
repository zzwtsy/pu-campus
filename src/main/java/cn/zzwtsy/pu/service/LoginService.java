package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.tools.MyHeaders;
import cn.zzwtsy.pu.tools.MyRequestBody;
import cn.zzwtsy.pu.utils.HttpHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static cn.zzwtsy.pu.api.ApiUrl.LOGIN_URL;

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
    public String getUserToken(String qqId, String userName, String password) {
        ObjectMapper mapper = new ObjectMapper();
        String response;
        try {
            response = HttpHelper.sendPost(LOGIN_URL, MyHeaders.baseHeaders(), MyRequestBody.loginBody(userName, password));
        } catch (IOException e) {
            PuCampus.INSTANCE.getLogger().error("发送登录请求失败", e);
            return "发送登录请求失败";
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
        if ("success".equals(message)) {
            String oauthToken = contentNode.get("oauth_token").asText();
            String oauthTokenSecret = contentNode.get("oauth_token_secret").asText();
            if (oauthTokenSecret != null && oauthToken != null) {
                UserService userService = new UserService();
                if (userService.getUser(qqId) != null) {
                    int updateUserStatus = userService.updateUser(String.valueOf(qqId), oauthToken, oauthTokenSecret);
                    if (updateUserStatus <= 0) {
                        return "登录失败";
                    } else {
                        return "true";
                    }
                } else {
                    int addUserStatus = userService.addUser(qqId, oauthToken, oauthTokenSecret);
                    if (addUserStatus <= 0) {
                        return "登录失败";
                    } else {
                        return "true";
                    }
                }
            }
            return "登录失败";
        }
        return message;
    }
}
