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
    public User getUser(long qqId) {
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
    public int addUser(long qqId, String uid, String oauthToken, String oauthTokenSecret) {
        return userDao.addUser(qqId, uid, oauthToken, oauthTokenSecret);
    }

    /**
     * 删除用户
     *
     * @param qqId qq号
     * @return 受影响的行数: >=1 删除成功
     */
    public int deleteUser(long qqId) {
        return userDao.deleteUser(qqId);
    }

    /**
     * 更新用户信息
     *
     * @param qqId             qq号
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return 受影响的行数: >=1 更新成功
     */
    public int updateUser(long qqId, String uid, String oauthToken, String oauthTokenSecret) {
        return userDao.updateUser(qqId, uid, oauthToken, oauthTokenSecret);
    }
}
