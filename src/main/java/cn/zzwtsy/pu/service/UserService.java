package cn.zzwtsy.pu.service;

import cn.zzwtsy.pu.bean.User;
import cn.zzwtsy.pu.dao.UserDao;

public class UserService {
    private final UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public User getUser(String qqId) {
        return userDao.getUserByQQId(qqId);
    }

    public int addUser(String qqId, String oauthToken, String oauthTokenSecret) {
        return userDao.addUser(qqId, oauthToken, oauthTokenSecret);
    }

    public int updateUser(String qqId, String oauthToken, String oauthTokenSecret) {
        return userDao.updateUser(qqId, oauthToken, oauthTokenSecret);
    }
}
