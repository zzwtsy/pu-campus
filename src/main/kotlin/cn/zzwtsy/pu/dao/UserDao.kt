package cn.zzwtsy.pu.dao

import cn.zzwtsy.pu.model.User
import cn.zzwtsy.pu.model.users
import cn.zzwtsy.pu.tools.ToolsKt
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.update

/**
 * 用户 Dao
 * @author zzwtsy
 * @date 2023/03/21
 */
object UserDao {
    private lateinit var database: Database

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
     * @return [Int]
     */
    fun addUser(qqId: Long, uid: String, oauthToken: String, oauthTokenSecret: String): Int {
        val md5 = ToolsKt.encryptionQqIdToMd5(qqId)
        val user = User(md5, uid, oauthToken, oauthTokenSecret)
        return database.users.add(user)
    }

    /**
     * 删除用户
     * @param [qqId] qq id
     * @return [Int]
     */
    fun deleteUser(qqId: Long): Int {
        val md5 = ToolsKt.encryptionQqIdToMd5(qqId)
        return database.users.find { it.qqId eq md5 }?.delete()!!
    }

    /**
     * 更新用户信息
     * @param [qqId] qq id
     * @param [uid] uid
     * @param [oauthToken] oauthToken
     * @param [oauthTokenSecret] oauthTokenSecret
     * @return [Int]
     */
    fun updateUser(qqId: Long, uid: String, oauthToken: String, oauthTokenSecret: String): Int {
        val md5 = ToolsKt.encryptionQqIdToMd5(qqId)
        val user = User(md5, uid, oauthToken, oauthTokenSecret)
        return database.users.update(user)
    }
}