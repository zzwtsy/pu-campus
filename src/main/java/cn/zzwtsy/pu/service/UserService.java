package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.bean.User;
import cn.zzwtsy.pu.dao.UserDao;

/**
 * 用户服务
 *
 * @author zzwtsy
 * @since 2022/12/23
 */
public class UserService {
    private final UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    /**
     * 获取用户信息
     *
     * @param qqId qq号
     * @return {@link User}
     */
    public User getUser(String qqId) {
        return userDao.getUserByQQId(qqId);
    }

    /**
     * 添加用户信息
     *
     * @param qqId             qq号
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return int
     */
    public int addUser(String qqId, String oauthToken, String oauthTokenSecret) {
        return userDao.addUser(qqId, oauthToken, oauthTokenSecret);
    }

    /**
     * 更新用户信息
     *
     * @param qqId             qq号
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return int
     */
    public int updateUser(String qqId, String oauthToken, String oauthTokenSecret) {
        return userDao.updateUser(qqId, oauthToken, oauthTokenSecret);
    }
}
