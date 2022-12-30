package cn.zzwtsy.pu.tools;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 请求体
 *
 * @author zzwtsy
 * @since 2022/11/30
 */
public class MyRequestBody {

    /**
     * 登录
     *
     * @param userName 用户名
     * @param password 密码
     * @return {@link RequestBody}
     */
    public static RequestBody loginBody(String userName, String password) {
        return new FormBody.Builder()
                .add("password", password)
                .add("email", userName)
                .add("client", "1")
                .build();
    }

    /**
     * 根据日期获取事件列表
     *
     * @param date             日期
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link RequestBody}
     */
    public static RequestBody calendarEventListBody(String date, String oauthToken, String oauthTokenSecret) {
        return new FormBody.Builder()
                .add("day", date)
                .add("oauth_token", oauthToken)
                .add("oauth_token_secret", oauthTokenSecret)
                .build();
    }

    /**
     * 新活动列表
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link RequestBody}
     */
    public static RequestBody newEventListBody(String oauthToken, String oauthTokenSecret) {
        return new FormBody.Builder()
                .add("page", "1")
                .add("oauth_token", oauthToken)
                .add("oauth_token_secret", oauthTokenSecret)
                .build();
    }

    /**
     * token
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link RequestBody}
     */
    public static RequestBody tokenBody(String oauthToken, String oauthTokenSecret) {
        return new FormBody.Builder()
                .add("oauth_token", oauthToken)
                .add("oauth_token_secret", oauthTokenSecret)
                .build();
    }

    /**
     * 待签到/签退列表
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @param userId           用户id
     * @param status           3: 待签退列表 4: 待签到列表
     * @param count            活动个数
     * @return {@link RequestBody}
     */
    public static RequestBody myEventListBody(String userId, String status, String count, String oauthToken, String oauthTokenSecret) {
        return new FormBody.Builder()
                .add("id", userId)
                .add("count", count)
                .add("status", status)
                .add("action", "join")
                .add("oauth_token", oauthToken)
                .add("oauth_token_secret", oauthTokenSecret)
                .build();
    }
}
