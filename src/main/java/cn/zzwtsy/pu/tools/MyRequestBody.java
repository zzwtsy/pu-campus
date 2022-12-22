package cn.zzwtsy.pu.tools;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import static cn.zzwtsy.pu.tools.MyStatic.setting;

/**
 * 请求体
 *
 * @author zzwtsy
 * @since 2022/11/30
 */
public class MyRequestBody {
    private static String oauthToken = setting.getOauthToken();
    private static String oauthTokenSecret = setting.getOauthTokenSecret();

    public static RequestBody loginBody(String userName, String password) {
        return new FormBody.Builder()
                .add("password", password)
                .add("email", userName)
                .add("client", "1")
                .build();
    }

    public static RequestBody calendarEventListBody(String date) {
        return new FormBody.Builder()
                .add("day", date)
                .add("oauth_token", oauthToken)
                .add("oauth_tokenSecret", oauthTokenSecret)
                .build();
    }
}
