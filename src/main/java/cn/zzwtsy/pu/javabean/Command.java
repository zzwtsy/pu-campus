package cn.zzwtsy.pu.javabean;

/**
 * 命令
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class Command {
    private String commandPrefix;
    private String login;
    private String queryNewEventList;
    private String querySignInEventList;
    private String querySignOutEventList;
    private String queryActivityDetailById;
    private String getCalendarEventList;

    public static Command INSTANCE = new Command();

    public String getCommandPrefix() {
        return commandPrefix;
    }

    public void setCommandPrefix(String commandPrefix) {
        this.commandPrefix = commandPrefix;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getQueryNewEventList() {
        return queryNewEventList;
    }

    public void setQueryNewEventList(String queryNewEventList) {
        this.queryNewEventList = queryNewEventList;
    }

    public String getQuerySignInEventList() {
        return querySignInEventList;
    }

    public void setQuerySignInEventList(String querySignInEventList) {
        this.querySignInEventList = querySignInEventList;
    }

    public String getQuerySignOutEventList() {
        return querySignOutEventList;
    }

    public void setQuerySignOutEventList(String querySignOutEventList) {
        this.querySignOutEventList = querySignOutEventList;
    }

    public String getQueryActivityDetailById() {
        return queryActivityDetailById;
    }

    public void setQueryActivityDetailById(String queryActivityDetailById) {
        this.queryActivityDetailById = queryActivityDetailById;
    }

    public String getGetCalendarEventList() {
        return getCalendarEventList;
    }

    public void setGetCalendarEventList(String getCalendarEventList) {
        this.getCalendarEventList = getCalendarEventList;
    }
}
