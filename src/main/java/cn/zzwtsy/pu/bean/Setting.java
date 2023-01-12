package cn.zzwtsy.pu.bean;

/**
 * 配置文件 Bean
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class Setting {
    public static final Setting INSTANCE = new Setting();
    private long groupId;
    private long adminId;
    private long botId;
    private String timedTaskTime;
    private String emailSuffix;

    public String getTimedTaskTime() {
        return timedTaskTime;
    }

    public Setting setTimedTaskTime(String timedTaskTime) {
        this.timedTaskTime = timedTaskTime;
        return this;
    }

    /**
     * 获取机器人qq号
     *
     * @return long
     */
    public long getBotId() {
        return botId;
    }

    /**
     * 设置机器人qq号
     *
     * @param botId 机器人qq号
     * @return {@link Setting}
     */
    public Setting setBotId(long botId) {
        this.botId = botId;
        return this;
    }

    /**
     * 获得电子邮件后缀
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
     * @return {@link Setting}
     */
    public Setting setEmailSuffix(String emailSuffix) {
        this.emailSuffix = emailSuffix;
        return this;
    }

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
     * @return {@link Setting}
     */
    public Setting setGroupId(long groupId) {
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
     * @return {@link Setting}
     */
    public Setting setAdminId(long adminId) {
        this.adminId = adminId;
        return this;
    }
}
