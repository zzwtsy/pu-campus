package cn.zzwtsy.pu.api;

/**
 * @author zzwtsy
 * @since 2022/11/28
 */
public class ApiUrl {
    public static final String LOGIN_URL = "https://pocketuni.net/index.php?app=api&mod=Sitelist&act=login";
    public static final String SCHOOLS_INFO_URL = "https://pocketuni.net/index.php?app=api&mod=Sitelist&act=getSchools";
    public static final String SIGN_IN_EVENT_LIST_URL = "https://pocketuni.net/index.php?app=api&mod=Event&act=myCanSignInEventList&page=1";
    public static final String SIGN_OUT_EVENT_LIST_URL = "https://pocketuni.net/index.php?app=api&mod=Event&act=myCanSignOutEventList&page=1";
    public static final String SIGN_RECORDS_URL = "https://pocketuni.net/index.php?app=api&mod=Event&act=signRecords";
    public static final String CALENDAR_EVENT_LIST_URL = "https://pocketuni.net/index.php?app=api&mod=Event&act=calendarEventList";
}
