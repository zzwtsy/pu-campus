package cn.zzwtsy.pu.api;

/**
 * @author zzwtsy
 * @since 2022/11/28
 */
public class ApiUrl {
    public static final String HOST = "https://pocketuni.net";
    public static final String LOGIN_URL = HOST + "/index.php?app=api&mod=Sitelist&act=login";
    public static final String SCHOOLS_INFO_URL = HOST + "/index.php?app=api&mod=Sitelist&act=getSchools";
    /**
     * 待签到事件列表
     */
    public static final String SIGN_IN_EVENT_LIST_URL = HOST + "/index.php?app=api&mod=Event&act=myCanSignInEventList&page=1";
    /**
     * 待签退事件列表
     */
    public static final String SIGN_OUT_EVENT_LIST_URL = HOST + "/index.php?app=api&mod=Event&act=myCanSignOutEventList&page=1";
    /**
     * 已签到列表
     */
    public static final String SIGN_RECORDS_URL = HOST + "/index.php?app=api&mod=Event&act=signRecords";
    /**
     * 根据日期获取活动列表
     */
    public static final String CALENDAR_EVENT_LIST_URL = HOST + "/index.php?app=api&mod=Event&act=calendarEventList";
}
