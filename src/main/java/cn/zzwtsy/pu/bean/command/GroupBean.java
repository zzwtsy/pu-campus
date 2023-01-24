package cn.zzwtsy.pu.bean.command;

/**
 * 群组可使用命令
 *
 * @author zzwtsy
 * @since 2023/01/24
 */
public class GroupBean {
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