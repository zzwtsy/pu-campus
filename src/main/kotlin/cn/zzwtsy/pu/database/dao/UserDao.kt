package cn.zzwtsy.pu.database.dao

import cn.zzwtsy.pu.database.entity.User
import cn.zzwtsy.pu.database.entity.users
import cn.zzwtsy.pu.tools.ToolsKt
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.update

lateinit var database: Database

/**
 * 用户 Dao
 * @author zzwtsy
 * @date 2023/03/21
 */
object UserDao {
    /**
     * 根据用户 qq 号获取用户信息
     * @param [qqId] qq id
     * @return [User?]
     */
    fun getUserByQqId(qqId: Long): User? {
        val md5 = ToolsKt.encryptionQqIdToMd5(qqId)
        return database.users.find { it.qqId eq md5 }
    }

    /**
     * 添加用户
     * @param [qqId] qq id
     * @param [uid] uid
     * @param [oauthToken] oauthToken
     * @param [oauthTokenSecret] oauthTokenSecret
     * @return [Boolean] true 添加用户信息成功，false 添加用户信息失败
     */
    fun addUser(qqId: Long, uid: String, oauthToken: String, oauthTokenSecret: String): Boolean {
        val md5 = ToolsKt.encryptionQqIdToMd5(qqId)
        val user = User(md5, uid, oauthToken, oauthTokenSecret)
        return database.users.add(user) > 0
    }

    /**
     * 删除用户
     * @param [qqId] qq id
     * @return [Boolean] true 删除用户信息成功，false 删除用户信息失败
     */
    fun deleteUser(qqId: Long): Boolean {
        val md5 = ToolsKt.encryptionQqIdToMd5(qqId)
        return database.users.find { it.qqId eq md5 }?.delete()!! > 0
    }

    /**
     * 更新用户信息
     * @param [qqId] qq id
     * @param [uid] uid
     * @param [oauthToken] oauthToken
     * @param [oauthTokenSecret] oauthTokenSecret
     * @return [Boolean] true 更新用户信息成功，false 更新用户信息失败
     */
    fun updateUser(qqId: Long, uid: String, oauthToken: String, oauthTokenSecret: String): Boolean {
        val md5 = ToolsKt.encryptionQqIdToMd5(qqId)
        val user = User(md5, uid, oauthToken, oauthTokenSecret)
        return database.users.update(user) > 0
    }
}