package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.bean.UserConfig;
import cn.zzwtsy.pu.tools.MyHeaders;
import cn.zzwtsy.pu.tools.MyRequestBody;
import cn.zzwtsy.pu.utils.HttpHelper;
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
     * @return boolean
     * @throws IOException ioexception
     */
    public boolean getUserToken(String userName, String password) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String sendPost = HttpHelper.sendPost(LOGIN_URL, MyHeaders.baseHeaders(), MyRequestBody.loginBody(userName, password));
        JsonNode jsonNode = mapper.readTree(sendPost);
        UserConfig.INSTANCE.setOauthToken(jsonNode.get("content").get("oauth_token").asText());
        UserConfig.INSTANCE.setOauthTokenSecret(jsonNode.get("content").get("oauth_token_secret").asText());
        return "".equals(UserConfig.INSTANCE.getOauthToken()) && "".equals(UserConfig.INSTANCE.getOauthTokenSecret());
    }
}
