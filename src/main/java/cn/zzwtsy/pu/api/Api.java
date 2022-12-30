package cn.zzwtsy.pu.api;

import cn.zzwtsy.pu.tools.MyHeaders;
import cn.zzwtsy.pu.tools.MyRequestBody;
import cn.zzwtsy.pu.utils.HttpHelper;

import java.io.IOException;

/**
 * @author zzwtsy
 * @since 2022/11/28
 */
public class Api {
    private final String HOST = "https://pocketuni.net";
    private final String SCHOOLS_INFO_URL = HOST + "/index.php?app=api&mod=Sitelist&act=getSchools";
    /**
     * 待签到事件列表
     */
    private final String SIGN_IN_EVENT_LIST_URL = HOST + "/index.php?app=api&mod=Event&act=myCanSignInEventList&page=1";
    /**
     * 待签退事件列表
     */
    public final String SIGN_OUT_EVENT_LIST_URL = HOST + "/index.php?app=api&mod=Event&act=myCanSignOutEventList&page=1";
    /**
     * 已签到列表
     */
    public final String SIGN_RECORDS_URL = HOST + "/index.php?app=api&mod=Event&act=signRecords";

    /**
     * 根据日期获取事件列表
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
     * 得到新事件列表
     *
     * @return {@link String}
     */
    public String getNewEventList(String oauthToken, String oauthTokenSecret) throws IOException {
        String newEventListUrl = HOST + "/index.php?app=api&mod=Event&act=newEventList";
        return HttpHelper.sendPost(newEventListUrl, MyHeaders.tokenHeaders(oauthToken, oauthTokenSecret), MyRequestBody.newEventListBody(oauthToken, oauthTokenSecret));
    }

    /**
     * 获取活动类学分信息
     *
     * @return {@link String}
     */
    public String getActiveCredit(String oauthToken, String oauthTokenSecret) throws IOException {
        String activeCreditUrl = HOST + "/index.php?app=api&mod=UserCredit&act=getEventActive&oauth_token=" + oauthToken + "&oauth_token_secret=" + oauthTokenSecret;
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
        String applyCreditUrl = HOST + "/index.php?app=api&mod=UserCredit&act=getEventApply&oauth_token=" + oauthToken + "&oauth_token_secret=" + oauthTokenSecret;
        return HttpHelper.sendGet(applyCreditUrl, MyHeaders.creditHeaders());
    }
}
