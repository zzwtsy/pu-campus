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
    private PublicBean publicBean;
    @JsonProperty("group")
    private GroupBean groupBean;
    @JsonProperty("private")
    private PrivateBean privateBean;
    @JsonProperty("admin")
    private AdminBean adminBean;

    /**
     * 获得 PublicBean
     *
     * @return {@link PublicBean}
     */
    public PublicBean getPublicBean() {
        return publicBean;
    }

    /**
     * 设置 PublicBean
     *
     * @param publicBean PublicBean
     */
    public void setPublicBean(PublicBean publicBean) {
        this.publicBean = publicBean;
    }

    /**
     * 获得GroupBean
     *
     * @return {@link GroupBean}
     */
    public GroupBean getGroupBean() {
        return groupBean;
    }

    /**
     * 设置 GroupBean
     *
     * @param groupBean GroupBean
     */
    public void setGroupBean(GroupBean groupBean) {
        this.groupBean = groupBean;
    }

    /**
     * 得到PrivateBean
     *
     * @return {@link PrivateBean}
     */
    public PrivateBean getPrivateBean() {
        return privateBean;
    }

    /**
     * 设置PrivateBean
     *
     * @param privateBean PrivateBean
     */
    public void setPrivateBean(PrivateBean privateBean) {
        this.privateBean = privateBean;
    }

    /**
     * 获得 AdminBean
     *
     * @return {@link AdminBean}
     */
    public AdminBean getAdminBean() {
        return adminBean;
    }

    /**
     * 设置 AdminBean
     *
     * @param adminBean AdminBean
     */
    public void setAdminBean(AdminBean adminBean) {
        this.adminBean = adminBean;
    }
}
