package cn.zzwtsy.pu.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 命令 Bean
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class Command {
    public static Command INSTANCE = new Command();
    /**
     * public : {
     * "commandPrefix":"#",
     * "help":"help"
     * }
     * group : {
     * "deleteUser":"删除我的信息",
     * "queryUserCreditInfo":"学分信息",
     * "querySignInEventList":"签到",
     * "querySignOutEventList":"签退",
     * "queryActivityDetailById":"活动信息",
     * "getCalendarEventList":"获取活动列表",
     * "queryUserEventEndUnissuedCreditList":"未发放学分活动"
     * }
     * private : {
     * "login":"登录",
     * "queryUserCreditInfo":"学分信息"
     * }
     * admin : {
     * "adminDeleteUser":"删除用户",
     * "addPublicToken":"添加tk",
     * "timedTask":"定时任务"
     * }
     */

    @JsonProperty("public")
    private PublicBean publicX;
    private GroupBean group;
    @JsonProperty("private")
    private PrivateBean privateX;
    private AdminBean admin;

    /**
     * 获得 PublicBean
     *
     * @return {@link PublicBean}
     */
    public PublicBean getPublicX() {
        return publicX;
    }

    /**
     * 设置 PublicBean
     *
     * @param publicX PublicBean
     */
    public void setPublicX(PublicBean publicX) {
        this.publicX = publicX;
    }

    /**
     * 获得GroupBean
     *
     * @return {@link GroupBean}
     */
    public GroupBean getGroup() {
        return group;
    }

    /**
     * 设置 GroupBean
     *
     * @param group GroupBean
     */
    public void setGroup(GroupBean group) {
        this.group = group;
    }

    /**
     * 得到PrivateBean
     *
     * @return {@link PrivateBean}
     */
    public PrivateBean getPrivateX() {
        return privateX;
    }

    /**
     * 设置PrivateBean
     *
     * @param privateX PrivateBean
     */
    public void setPrivateX(PrivateBean privateX) {
        this.privateX = privateX;
    }

    /**
     * 获得 AdminBean
     *
     * @return {@link AdminBean}
     */
    public AdminBean getAdmin() {
        return admin;
    }

    /**
     * 设置 AdminBean
     *
     * @param admin AdminBean
     */
    public void setAdmin(AdminBean admin) {
        this.admin = admin;
    }

    /**
     * 公共命令
     *
     * @author zzwtsy
     * @since 2023/01/24
     */
    public static class PublicBean {
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

    /**
     * 群组可使用命令
     *
     * @author zzwtsy
     * @since 2023/01/24
     */
    public static class GroupBean {
        /**
         * deleteUser : 删除我的信息
         * queryUserCreditInfo : 学分信息
         * querySignInEventList : 签到
         * querySignOutEventList : 签退
         * queryActivityDetailById : 活动信息
         * getCalendarEventList : 获取活动列表
         * queryUserEventEndUnissuedCreditList : 未发放学分活动
         */

        private String queryUserCreditInfo;
        private String querySignInEventList;
        private String querySignOutEventList;
        private String queryActivityDetailById;
        private String getCalendarEventList;
        private String queryUserEventEndUnissuedCreditList;


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
         * @return {@link GroupBean}
         */
        public GroupBean setQueryUserCreditInfo(String queryUserCreditInfo) {
            this.queryUserCreditInfo = queryUserCreditInfo;
            return this;
        }

        /**
         * 获得获取待签到活动命令
         *
         * @return {@link String}
         */
        public String getQuerySignInEventList() {
            return querySignInEventList;
        }

        /**
         * 设置获取待签到活动命令
         *
         * @param querySignInEventList 获取待签到活动命令
         * @return {@link GroupBean}
         */
        public GroupBean setQuerySignInEventList(String querySignInEventList) {
            this.querySignInEventList = querySignInEventList;
            return this;
        }

        /**
         * 获得获取待签退活动命令
         *
         * @return {@link String}
         */
        public String getQuerySignOutEventList() {
            return querySignOutEventList;
        }

        /**
         * 设置获取待签退活动命令
         *
         * @param querySignOutEventList 获取待签退活动命令
         * @return {@link GroupBean}
         */
        public GroupBean setQuerySignOutEventList(String querySignOutEventList) {
            this.querySignOutEventList = querySignOutEventList;
            return this;
        }

        /**
         * 获得根据活动id查询活动详情命令
         *
         * @return {@link String}
         */
        public String getQueryActivityDetailById() {
            return queryActivityDetailById;
        }

        /**
         * 设置根据活动id查询活动详情命令
         *
         * @param queryActivityDetailById 根据活动id查询活动详情命令
         * @return {@link GroupBean}
         */
        public GroupBean setQueryActivityDetailById(String queryActivityDetailById) {
            this.queryActivityDetailById = queryActivityDetailById;
            return this;
        }

        /**
         * 获得根据日期获取活动列表命令
         *
         * @return {@link String}
         */
        public String getGetCalendarEventList() {
            return getCalendarEventList;
        }

        /**
         * 设置根据日期获取活动列表命令
         *
         * @param getCalendarEventList 根据日期获取活动列表命令
         * @return {@link GroupBean}
         */
        public GroupBean setGetCalendarEventList(String getCalendarEventList) {
            this.getCalendarEventList = getCalendarEventList;
            return this;
        }

        /**
         * 获得获取活动已结束但未发放学分的活动命令
         *
         * @return {@link String}
         */
        public String getQueryUserEventEndUnissuedCreditList() {
            return queryUserEventEndUnissuedCreditList;
        }

        /**
         * 设置获取活动已结束但未发放学分的活动命令
         *
         * @param queryUserEventEndUnissuedCreditList 获取活动已结束但未发放学分的活动命令
         * @return {@link GroupBean}
         */
        public GroupBean setQueryUserEventEndUnissuedCreditList(String queryUserEventEndUnissuedCreditList) {
            this.queryUserEventEndUnissuedCreditList = queryUserEventEndUnissuedCreditList;
            return this;
        }
    }

    /**
     * 私聊可适用命令
     *
     * @author zzwtsy
     * @since 2023/01/24
     */
    public static class PrivateBean {
        /**
         * login : 登录
         * queryUserCreditInfo : 学分信息
         */

        private String login;
        private String deleteUser;

        /**
         * 获取用户删除自身信息命令
         *
         * @return {@link String}
         */
        public String getDeleteUser() {
            return deleteUser;
        }

        /**
         * 设置用户删除自身信息命令
         *
         * @param deleteUser 用户删除自身信息命令
         * @return {@link PrivateBean}
         */
        public PrivateBean setDeleteUser(String deleteUser) {
            this.deleteUser = deleteUser;
            return this;
        }

        /**
         * 获得登录 pu 命令
         *
         * @return {@link String}
         */
        public String getLogin() {
            return login;
        }

        /**
         * 设置登录 pu 校园命令
         *
         * @param login 登录 pu 校园命令
         * @return {@link PrivateBean}
         */
        public PrivateBean setLogin(String login) {
            this.login = login;
            return this;
        }
    }

    /**
     * 管理员可使用命令
     *
     * @author zzwtsy
     * @since 2023/01/24
     */
    public static class AdminBean {
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
}
