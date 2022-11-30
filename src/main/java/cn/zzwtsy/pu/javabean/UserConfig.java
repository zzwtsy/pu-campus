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

    /**
     * 获取QQ群号
     *
     * @return long
     */
    public long getGroupId() {
        return groupId;
    }

    /**
     * 设置QQ群号
     *
     * @param groupId QQ群号
     * @return {@link UserConfig}
     */
    public UserConfig setGroupId(long groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * 获取管理员qq号
     *
     * @return long
     */
    public long getAdminId() {
        return adminId;
    }

    /**
     * 设置管理员qq号
     *
     * @param adminId 管理员qq号
     * @return {@link UserConfig}
     */
    public UserConfig setAdminId(long adminId) {
        this.adminId = adminId;
        return this;
    }

    /**
     * 获取电子邮件后缀
     *
     * @return {@link String}
     */
    public String getEmailSuffix() {
        return emailSuffix;
    }

    /**
     * 设置电子邮件后缀
     *
     * @param emailSuffix 电子邮件后缀
     * @return {@link UserConfig}
     */
    public UserConfig setEmailSuffix(String emailSuffix) {
        this.emailSuffix = emailSuffix;
        return this;
    }

    /**
     * 获取setOauthToken
     *
     * @return {@link String}
     */
    public String getOauthToken() {
        return oauthToken;
    }

    /**
     * 设置setOauthToken
     *
     * @param oauthToken oauth令牌
     * @return {@link UserConfig}
     */
    public UserConfig setOauthToken(String oauthToken) {
        this.oauthToken = oauthToken;
        return this;
    }

    /**
     * 获取oauthTokenSecret
     *
     * @return {@link String}
     */
    public String getOauthTokenSecret() {
        return oauthTokenSecret;
    }

    /**
     * 设置oauthTokenSecret
     *
     * @param oauthTokenSecret oauth令牌秘密
     * @return {@link UserConfig}
     */
    public UserConfig setOauthTokenSecret(String oauthTokenSecret) {
        this.oauthTokenSecret = oauthTokenSecret;
        return this;
    }
}
