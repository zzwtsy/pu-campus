package cn.zzwtsy.pu.service

import cn.zzwtsy.pu.dao.UserDao
import cn.zzwtsy.pu.model.User

object UserService {
    /**
     * 获取用户信息
     * @param [qqId] qq号
     * @return [User?]
     */
    fun getUser(qqId: Long): User? {
        return UserDao.getUserByQqId(qqId)
    }

    /**
     * 添加用户信息
     * @param [qqId] qq号
     * @param [uid] uid
     * @param [oauthToken] oauthToken
     * @param [oauthTokenSecret] oauthTokenSecret
     * @return [Boolean]
     */
    fun addUser(qqId: Long, uid: String?, oauthToken: String?, oauthTokenSecret: String?): Boolean {
        return UserDao.addUser(qqId, uid!!, oauthToken!!, oauthTokenSecret!!) > 0
    }

    /**
     * 删除用户
     * @param [qqId] qq号
     * @return [Boolean]
     */
    fun deleteUser(qqId: Long): Boolean {
        return UserDao.deleteUser(qqId) > 0
    }

    /**
     * 更新用户信息
     * @param [qqId] qq号
     * @param [uid] uid
     * @param [oauthToken] oauthToken
     * @param [oauthTokenSecret] oauthTokenSecret
     * @return [Boolean]
     */
    fun updateUser(qqId: Long, uid: String, oauthToken: String, oauthTokenSecret: String): Boolean {
        return UserDao.updateUser(qqId, uid, oauthToken, oauthTokenSecret) > 0
    }
}