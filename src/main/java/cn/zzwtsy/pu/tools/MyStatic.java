package cn.zzwtsy.pu.tools;

/**
 * @author zzwtsy
 * @since 2022/11/28
 */
public class MyStatic {
    public static String oauthToken = null;
    public static String oauthTokenSecret = null;
    public static final String LOGIN_URL = "https://pocketuni.net/index.php?app=api&mod=Sitelist&act=login";
    public static final String GET_SCHOOLS_INFO_URL = "https://pocketuni.net/index.php?app=api&mod=Sitelist&act=getSchools";
    public static final String GET_USER_SIGN_IN_EVENT_LIST_URL = "https://pocketuni.net/index.php?app=api&mod=Event&act=myCanSignInEventList&page=1";
    public static final String GET_USER_SIGN_OUT_EVENT_LIST_URL = "https://pocketuni.net/index.php?app=api&mod=Event&act=myCanSignOutEventList&page=1";
    public static final String GET_USER_SIGN_RECORDS_URL = "https://pocketuni.net/index.php?app=api&mod=Event&act=signRecords";
}
