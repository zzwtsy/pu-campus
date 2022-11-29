package cn.zzwtsy.pu.javabean;

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

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public Command setCommandPrefix(String commandPrefix) {
        this.commandPrefix = commandPrefix;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Command setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getQueryNewEventList() {
        return queryNewEventList;
    }

    public Command setQueryNewEventList(String queryNewEventList) {
        this.queryNewEventList = queryNewEventList;
        return this;
    }

    public String getQuerySignInEventList() {
        return querySignInEventList;
    }

    public Command setQuerySignInEventList(String querySignInEventList) {
        this.querySignInEventList = querySignInEventList;
        return this;
    }

    public String getQuerySignOutEventList() {
        return querySignOutEventList;
    }

    public Command setQuerySignOutEventList(String querySignOutEventList) {
        this.querySignOutEventList = querySignOutEventList;
        return this;
    }

    public String getQueryActivityDetailById() {
        return queryActivityDetailById;
    }

    public Command setQueryActivityDetailById(String queryActivityDetailById) {
        this.queryActivityDetailById = queryActivityDetailById;
        return this;
    }

    public String getGetCalendarEventList() {
        return getCalendarEventList;
    }

    public Command setGetCalendarEventList(String getCalendarEventList) {
        this.getCalendarEventList = getCalendarEventList;
        return this;
    }
}
