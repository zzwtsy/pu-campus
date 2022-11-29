package cn.zzwtsy.pu.javabean;

/**
 * 配置文件
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class UserConfig {
    public static UserConfig INSTANCE = new UserConfig();
    private long groupId;
    private long adminId;
    private String emailSuffix;
    private String oauthToken;
    private String oauthTokenSecret;

    public long getGroupId() {
        return groupId;
    }

    public UserConfig setGroupId(long groupId) {
        this.groupId = groupId;
        return this;
    }

    public long getAdminId() {
        return adminId;
    }

    public UserConfig setAdminId(long adminId) {
        this.adminId = adminId;
        return this;
    }

    public String getEmailSuffix() {
        return emailSuffix;
    }

    public UserConfig setEmailSuffix(String emailSuffix) {
        this.emailSuffix = emailSuffix;
        return this;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public UserConfig setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
        return this;
    }

    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    public UserConfig setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
        return this;
    }
}
