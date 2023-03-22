package cn.zzwtsy.pu.database.entity

import org.ktorm.entity.Entity
import org.ktorm.ksp.api.PrimaryKey
import org.ktorm.ksp.api.Table

/**
 * 数据库用户表实体类
 * @author zzwtsy
 * @date 2023/03/21
 * @constructor 创建[User]
 */
@Table
interface User : Entity<User> {
    @PrimaryKey
    var qqId: String
    var uid: String
    var oauthToken: String
    var oauthTokenSecret: String
}