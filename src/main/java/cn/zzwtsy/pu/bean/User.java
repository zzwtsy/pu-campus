package cn.zzwtsy.pu.bean;

/**
 * 用户 Bean
 *
 * @author zzwtsy
 *
 * @since 2022/12/23
 */
public class User {
    private String qqId;
    private String oauthToken;
    private String oauthTokenSecret;

    public String getQqId() {
        return qqId;
    }

    public void setQqId(String qqId) {
        this.qqId = qqId;
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
