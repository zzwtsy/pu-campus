package cn.zzwtsy.pu.bean;

/**
 * 配置文件
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class UserConfig {
    public static final UserConfig INSTANCE = new UserConfig();
    private long groupId;
    private long adminId;

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
}
