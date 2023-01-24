package cn.zzwtsy.pu.bean.command;

/**
 * 公共命令
 *
 * @author zzwtsy
 * @since 2023/01/24
 */
public class PublicBean {
    /**
     * commandPrefix : #
     * help : help
     */

    private String commandPrefix;
    private String help;

    /**
     * 获得命令前缀
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
     * @return {@link PublicBean}
     */
    public PublicBean setCommandPrefix(String commandPrefix) {
        this.commandPrefix = commandPrefix;
        return this;
    }

    /**
     * 获得获取帮助信息命令
     *
     * @return {@link String}
     */
    public String getHelp() {
        return help;
    }

    /**
     * 设置获取帮助信息命令
     *
     * @param help 获取帮助信息命令
     * @return {@link PublicBean}
     */
    public PublicBean setHelp(String help) {
        this.help = help;
        return this;
    }
}
