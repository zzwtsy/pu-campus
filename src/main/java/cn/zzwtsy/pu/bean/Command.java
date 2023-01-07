package cn.zzwtsy.pu.bean;

/**
 * 命令 Bean
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class Command {
    public static final Command INSTANCE = new Command();
    /**
     * 命令前缀
     */
    private String commandPrefix;
    /**
     * 登录命令
     */
    private String login;
    /**
     * 获取帮助信息
     */
    private String help;
    /**
     * 删除用户信息
     */
    private String deleteUser;
    /**
     * 管理员删除用户信息
     */
    private String adminDeleteUser;
    /**
     * 查询新活动列表
     */
    private String queryNewEventList;
    /**
     * 查询用户信用信息
     */
    private String queryUserCreditInfo;
    /**
     * 查询签到活动列表
     */
    private String querySignInEventList;
    /**
     * 查询签退活动列表
     */
    private String querySignOutEventList;
    /**
     * 通过id查询活动详细信息
     */
    private String queryActivityDetailById;
    /**
     * 根据日期获取活动列表
     */
    private String getCalendarEventList;

    /**
     * 获取查询用户学分信息命令
     *
     * @return {@link String}
     */
    public String getQueryUserCreditInfo() {
        return queryUserCreditInfo;
    }

    /**
     * 设置查询用户学分信息命令
     *
     * @param queryUserCreditInfo 查询用户学分信息命令
     * @return {@link Command}
     */
    public Command setQueryUserCreditInfo(String queryUserCreditInfo) {
        this.queryUserCreditInfo = queryUserCreditInfo;
        return this;
    }

    /**
     * 获取帮助命令
     *
     * @return {@link String}
     */
    public String getHelp() {
        return help;
    }

    /**
     * 设置帮助命令
     *
     * @param help 帮助命令
     * @return {@link Command}
     */
    public Command setHelp(String help) {
        this.help = help;
        return this;
    }

    /**
     * 得获取管理员删除用户命令
     *
     * @return {@link String}
     */
    public String getAdminDeleteUser() {
        return adminDeleteUser;
    }

    /**
     * 设置管理员删除用户命令
     *
     * @param adminDeleteUser 管理员删除用户命令
     * @return {@link Command}
     */
    public Command setAdminDeleteUser(String adminDeleteUser) {
        this.adminDeleteUser = adminDeleteUser;
        return this;
    }

    /**
     * 获取删除用户命令
     *
     * @return {@link String}
     */
    public String getDeleteUser() {
        return deleteUser;
    }

    /**
     * 设置删除用户命令
     *
     * @param deleteUser 删除用户
     */
    public Command setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
        return this;
    }

    /**
     * 获取命令前缀
     *
     * @return {@link String}
     */
    public String getCommandPrefix() {
        return commandPrefix;
    }

    /**
     * 设置命令前缀
     *
     * @param commandPrefix 命令前缀
     * @return {@link Command}
     */
    public Command setCommandPrefix(String commandPrefix) {
        this.commandPrefix = commandPrefix;
        return this;
    }

    /**
     * 获取登录命令
     *
     * @return {@link String}
     */
    public String getLogin() {
        return login;
    }

    /**
     * 设置登录命令
     *
     * @param login 登录命令
     * @return {@link Command}
     */
    public Command setLogin(String login) {
        this.login = login;
        return this;
    }

    /**
     * 获取查询新活动列表命令
     *
     * @return {@link String}
     */
    public String getQueryNewEventList() {
        return queryNewEventList;
    }

    /**
     * 设置查询新活动列表命令
     *
     * @param queryNewEventList 查询新活动列表命令
     * @return {@link Command}
     */
    public Command setQueryNewEventList(String queryNewEventList) {
        this.queryNewEventList = queryNewEventList;
        return this;
    }

    /**
     * 获取签到活动列表命令
     *
     * @return {@link String}
     */
    public String getQuerySignInEventList() {
        return querySignInEventList;
    }

    /**
     * 设置查询签到活动列表命令
     *
     * @param querySignInEventList 查询签到活动列表命令
     * @return {@link Command}
     */
    public Command setQuerySignInEventList(String querySignInEventList) {
        this.querySignInEventList = querySignInEventList;
        return this;
    }

    /**
     * 获取签退活动列表命令
     *
     * @return {@link String}
     */
    public String getQuerySignOutEventList() {
        return querySignOutEventList;
    }

    /**
     * 设置签退活动列表命令
     *
     * @param querySignOutEventList 查询签退活动列表命令
     * @return {@link Command}
     */
    public Command setQuerySignOutEventList(String querySignOutEventList) {
        this.querySignOutEventList = querySignOutEventList;
        return this;
    }

    /**
     * 获取通过id查询活动详细信息命令
     *
     * @return {@link String}
     */
    public String getQueryActivityDetailById() {
        return queryActivityDetailById;
    }

    /**
     * 设置按id设置查询活动详细信息命令
     *
     * @param queryActivityDetailById 按id查询活动详情
     * @return {@link Command}
     */
    public Command setQueryActivityDetailById(String queryActivityDetailById) {
        this.queryActivityDetailById = queryActivityDetailById;
        return this;
    }

    /**
     * 获取通过日期获取活动列表命令
     *
     * @return {@link String}
     */
    public String getGetCalendarEventList() {
        return getCalendarEventList;
    }

    /**
     * 设置通过日期获取活动列表命令
     *
     * @param getCalendarEventList 通过日期获取活动列表命令
     * @return {@link Command}
     */
    public Command setGetCalendarEventList(String getCalendarEventList) {
        this.getCalendarEventList = getCalendarEventList;
        return this;
    }
}
