package cn.zzwtsy.pu.bean;

/**
 * pu活动 Bean
 *
 * @author zzwtsy
 * @since 2022/12/23
 */
public class Event {
    private String id;
    private String title;
    private String sTime;
    private String eTime;
    private String startline;
    private String deadline;
    private String address;
    private String description;
    private String creditName;
    private String isNeedSignOut;


    /**
     * 获取活动是否需要签退
     * 0:无需签退
     *
     * @return {@link String}
     */
    public String getIsNeedSignOut() {
        return isNeedSignOut;
    }

    /**
     * 设置活动是否需要签退
     *
     * @param isNeedSignOut 活动是否需要签退
     */
    public void setIsNeedSignOut(String isNeedSignOut) {
        this.isNeedSignOut = isNeedSignOut;
    }

    /**
     * 获取活动id
     *
     * @return {@link String}
     */
    public String getId() {
        return id;
    }

    /**
     * 设置活动id
     *
     * @param id 活动id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获得活动标题
     *
     * @return {@link String}
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置活动标题
     *
     * @param title 活动标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获得活动开始时间
     *
     * @return {@link String}
     */
    public String getStartTime() {
        return sTime;
    }

    /**
     * 设置活动开始时间
     *
     * @param sTime 活动开始时间
     */
    public void setStartTime(String sTime) {
        this.sTime = sTime;
    }

    /**
     * 获取活动结束时间
     *
     * @return {@link String}
     */
    public String getEndTime() {
        return eTime;
    }

    /**
     * 设置活动结束时间
     *
     * @param eTime 活动结束时间
     */
    public void setEndTime(String eTime) {
        this.eTime = eTime;
    }

    /**
     * 获取活动报名开始时间
     *
     * @return {@link String}
     */
    public String getStartline() {
        return startline;
    }

    /**
     * 设置活动报名开始时间
     *
     * @param startline 活动报名开始时间
     */
    public void setStartline(String startline) {
        this.startline = startline;
    }

    /**
     * 获取活动报名结束时间
     *
     * @return {@link String}
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * 设置活动报名结束时间
     *
     * @param deadline 活动报名结束时间
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * 获取活动地址
     *
     * @return {@link String}
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置活动地址
     *
     * @param address 活动地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取活动简介
     *
     * @return {@link String}
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置活动简介
     *
     * @param description 活动简介
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * 获取学分类型
     *
     * @return {@link String}
     */
    public String getCreditName() {
        return creditName;
    }

    /**
     * 设置学分类型
     *
     * @param creditName 学分类型
     */
    public void setCreditName(String creditName) {
        this.creditName = creditName;
    }

}
