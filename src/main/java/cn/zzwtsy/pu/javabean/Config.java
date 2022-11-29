package cn.zzwtsy.pu.javabean;

/**
 * 配置文件
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class Config {
    private long groupId;
    private long adminId;
    private String emailSuffix;
    private String oauthToken;
    private String oauthTokenSecret;

    public static Config INSTANCE = new Config();

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getEmailSuffix() {
        return emailSuffix;
    }

    public void setEmailSuffix(String emailSuffix) {
        this.emailSuffix = emailSuffix;
    }

    public String getOauthToken() {
        return oauthToken;
    }

    public void setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
    }

    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    public void setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
    }
}
