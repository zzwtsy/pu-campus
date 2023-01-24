package cn.zzwtsy.pu.bean.command;

/**
 * 私聊可适用命令
 *
 * @author zzwtsy
 * @since 2023/01/24
 */
public class PrivateBean {
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