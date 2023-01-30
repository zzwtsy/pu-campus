package cn.zzwtsy.pu.api;

import cn.zzwtsy.pu.tools.MyHeaders;
import cn.zzwtsy.pu.tools.MyRequestBody;
import cn.zzwtsy.pu.utils.HttpHelper;
import java.io.IOException;

/**
 * pu 校园 Api
 *
 * @author zzwtsy
 * @since 2022/11/28
 */
public class Api {
    private final String HOST = "https://pocketuni.net";

    /**
     * 活动已结束未发放学分列表
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @param userId           用户id
     * @param count            活动个数
     * @param page             页面
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String getUserEventEndUnissuedCreditList(String userId, String count, String page, String oauthToken, String oauthTokenSecret)
            throws IOException {
        String myEventListUrl = HOST + "/index.php?app=api&mod=Event&act=myEventList";
        return HttpHelper.sendPost(myEventListUrl, MyHeaders.baseHeaders(),
                MyRequestBody.myEventListBody(userId, "0", count, page, oauthToken, oauthTokenSecret));
    }

    /**
     * 获得待签退活动列表
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String getUserCanSingOutEventList(String oauthToken, String oauthTokenSecret) throws IOException {
        String signOutEventListUrl = HOST + "/index.php?app=api&mod=Event&act=myCanSignOutEventList&page=1";
        return HttpHelper.sendPost(signOutEventListUrl, MyHeaders.baseHeaders(),
                MyRequestBody.tokenBody(oauthToken, oauthTokenSecret));
    }

    /**
     * 获得待签到活动列表
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String getUserCanSignInEventList(String oauthToken, String oauthTokenSecret) throws IOException {
        String signInEventListUrl = HOST + "/index.php?app=api&mod=Event&act=myCanSignInEventList&page=1";
        return HttpHelper.sendPost(signInEventListUrl, MyHeaders.baseHeaders(),
                MyRequestBody.tokenBody(oauthToken, oauthTokenSecret));
    }

    /**
     * 根据日期获取活动列表
     *
     * @param date             日期
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String getCalendarEventList(String date, String oauthToken, String oauthTokenSecret) throws IOException {
        String calendarEventListUrl = HOST + "/index.php?app=api&mod=Event&act=calendarEventList";
        return HttpHelper.sendPost(calendarEventListUrl,
                MyHeaders.tokenHeaders(oauthToken, oauthTokenSecret),
                MyRequestBody.calendarEventListBody(date, oauthToken, oauthTokenSecret));
    }

    /**
     * 获取登录信息
     *
     * @param userName 用户名
     * @param password 密码
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String getLoginInfo(String userName, String password) throws IOException {
        String loginUrl = HOST + "/index.php?app=api&mod=Sitelist&act=login";
        return HttpHelper.sendPost(loginUrl, MyHeaders.baseHeaders(), MyRequestBody.loginBody(userName, password));
    }

    /**
     * 获取新活动列表
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String getNewEventList(String oauthToken, String oauthTokenSecret) throws IOException {
        String newEventListUrl = HOST + "/index.php?app=api&mod=Event&act=newEventList";
        return HttpHelper.sendPost(newEventListUrl, MyHeaders.tokenHeaders(oauthToken, oauthTokenSecret),
                MyRequestBody.newEventListBody(oauthToken, oauthTokenSecret));
    }

    /**
     * 获取活动类学分信息
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String getActiveCredit(String oauthToken, String oauthTokenSecret) throws IOException {
        String activeCreditUrl = HOST + "/index.php?app=api&mod=UserCredit&act=getEventActive&oauth_token=" + oauthToken
                + "&oauth_token_secret=" + oauthTokenSecret;
        return HttpHelper.sendGet(activeCreditUrl, MyHeaders.creditHeaders());
    }

    /**
     * 获取申请类学分信息
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String getApplyCredit(String oauthToken, String oauthTokenSecret) throws IOException {
        String applyCreditUrl = HOST + "/index.php?app=api&mod=UserCredit&act=getEventApply&oauth_token=" + oauthToken
                + "&oauth_token_secret=" + oauthTokenSecret;
        return HttpHelper.sendGet(applyCreditUrl, MyHeaders.creditHeaders());
    }

    /**
     * 获取用户信息
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return {@link String}
     * @throws IOException ioexception
     */
    public String getUserInfo(String oauthToken, String oauthTokenSecret) throws IOException {
        String userInfoUrl = HOST + "/api/User/personalCenter?oauth_token=" + oauthToken + "&oauth_token_secret=" + oauthTokenSecret;
        return HttpHelper.sendGet(userInfoUrl, MyHeaders.baseHeaders());
    }
}
