package cn.zzwtsy.pu.bean;

/**
 * 命令
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class Command {
    public static Command INSTANCE = new Command();
    private String commandPrefix;
    private String login;
    private String queryNewEventList;
    private String querySignInEventList;
    private String querySignOutEventList;
    private String queryActivityDetailById;
    private String getCalendarEventList;

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
     * 获取登录
     *
     * @return {@link String}
     */
    public String getLogin() {
        return login;
    }

    /**
     * 设置登录
     *
     * @param login 登录
     * @return {@link Command}
     */
    public Command setLogin(String login) {
        this.login = login;
        return this;
    }

    /**
     * 获取查询新事件列表
     *
     * @return {@link String}
     */
    public String getQueryNewEventList() {
        return queryNewEventList;
    }

    /**
     * 设置查询新事件列表
     *
     * @param queryNewEventList 查询新事件列表
     * @return {@link Command}
     */
    public Command setQueryNewEventList(String queryNewEventList) {
        this.queryNewEventList = queryNewEventList;
        return this;
    }

    /**
     * 获取查询登录事件列表
     *
     * @return {@link String}
     */
    public String getQuerySignInEventList() {
        return querySignInEventList;
    }

    /**
     * 在事件列表中设置查询签名
     *
     * @param querySignInEventList 查询登录事件列表
     * @return {@link Command}
     */
    public Command setQuerySignInEventList(String querySignInEventList) {
        this.querySignInEventList = querySignInEventList;
        return this;
    }

    /**
     * 获取查询注销事件列表
     *
     * @return {@link String}
     */
    public String getQuerySignOutEventList() {
        return querySignOutEventList;
    }

    /**
     * 设置查询注销事件列表
     *
     * @param querySignOutEventList 查询注销事件列表
     * @return {@link Command}
     */
    public Command setQuerySignOutEventList(String querySignOutEventList) {
        this.querySignOutEventList = querySignOutEventList;
        return this;
    }

    /**
     * 通过id获取查询活动详细信息
     *
     * @return {@link String}
     */
    public String getQueryActivityDetailById() {
        return queryActivityDetailById;
    }

    /**
     * 按id设置查询活动明细
     *
     * @param queryActivityDetailById 按id查询活动详情
     * @return {@link Command}
     */
    public Command setQueryActivityDetailById(String queryActivityDetailById) {
        this.queryActivityDetailById = queryActivityDetailById;
        return this;
    }

    /**
     * 获取日历事件列表
     *
     * @return {@link String}
     */
    public String getGetCalendarEventList() {
        return getCalendarEventList;
    }

    /**
     * 设置获取日历事件列表
     *
     * @param getCalendarEventList 获取日历事件列表
     * @return {@link Command}
     */
    public Command setGetCalendarEventList(String getCalendarEventList) {
        this.getCalendarEventList = getCalendarEventList;
        return this;
    }
}
