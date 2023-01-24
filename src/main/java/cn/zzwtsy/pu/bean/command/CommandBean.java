package cn.zzwtsy.pu.bean.command;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 命令 Bean
 *
 * @author zzwtsy
 * @since 2022/11/29
 */
public class CommandBean {
    public static CommandBean INSTANCE = new CommandBean();

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


}
