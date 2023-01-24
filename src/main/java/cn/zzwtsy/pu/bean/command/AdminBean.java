package cn.zzwtsy.pu.bean.command;

/**
 * 管理员可使用命令
 *
 * @author zzwtsy
 * @since 2023/01/24
 */
public class AdminBean {
    /**
     * adminDeleteUser : 删除用户
     * addPublicToken : 添加tk
     * timedTask : 定时任务
     */

    private String adminDeleteUser;
    private String addPublicToken;
    private String timedTask;

    /**
     * 获得管理员删除用户信息命令
     *
     * @return {@link String}
     */
    public String getAdminDeleteUser() {
        return adminDeleteUser;
    }

    /**
     * 设置管理员删除用户信息命令
     *
     * @param adminDeleteUser 管理员删除用户信息命令
     * @return {@link AdminBean}
     */
    public AdminBean setAdminDeleteUser(String adminDeleteUser) {
        this.adminDeleteUser = adminDeleteUser;
        return this;
    }

    /**
     * 获得添加公共 token 命令
     *
     * @return {@link String}
     */
    public String getAddPublicToken() {
        return addPublicToken;
    }

    /**
     * 设置添加公共 token 命令
     *
     * @param addPublicToken 添加公共 token 命令
     * @return {@link AdminBean}
     */
    public AdminBean setAddPublicToken(String addPublicToken) {
        this.addPublicToken = addPublicToken;
        return this;
    }

    /**
     * 获得设置定时任务时间命令
     *
     * @return {@link String}
     */
    public String getTimedTask() {
        return timedTask;
    }

    /**
     * 设置设置定时任务时间命令
     *
     * @param timedTask 设置定时任务时间命令
     * @return {@link AdminBean}
     */
    public AdminBean setTimedTask(String timedTask) {
        this.timedTask = timedTask;
        return this;
    }
}
