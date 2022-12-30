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
        return userDao.getUserByQqId(qqId);
    }

    /**
     * 添加用户信息
     *
     * @param qqId             qq号
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return 受影响的行数: >=1 添加成功
     */
    public int addUser(String qqId, String uid, String oauthToken, String oauthTokenSecret) {
        return userDao.addUser(qqId, oauthToken, oauthTokenSecret);
    }

    /**
     * 删除用户
     *
     * @param qqId qq号
     * @return 受影响的行数: >=1 删除成功
     */
    public int deleteUser(String qqId) {
        return userDao.delUser(qqId);
    }

    /**
     * 更新用户信息
     *
     * @param qqId             qq号
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return 受影响的行数: >=1 更新成功
     */
    public int updateUser(String qqId, String uid, String oauthToken, String oauthTokenSecret) {
        return userDao.updateUser(qqId, oauthToken, oauthTokenSecret);
    }
}
