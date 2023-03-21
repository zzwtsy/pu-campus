package cn.zzwtsy.pu.database.dao

import cn.zzwtsy.pu.database.data.User
import cn.zzwtsy.pu.database.data.Users
import cn.zzwtsy.pu.database.data.users
import cn.zzwtsy.pu.utils.EncryptionKt
import org.ktorm.entity.find

class UserDao:BaseDao<User,Users>(Users) {
    fun getUserByQqId(qqId: Long): User? {
        val md5 = EncryptionKt.toMd5(qqId.toString(), qqId.toString())
        return database.users.find { it.qqId }
    }
}