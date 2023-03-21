package cn.zzwtsy.pu.database.entity

import org.ktorm.ksp.api.PrimaryKey
import org.ktorm.ksp.api.Table

/**
 * 数据库用户表实体类
 * @author zzwtsy
 * @date 2023/03/21
 * @constructor 创建[User]
 * @param [qqId] qq id
 * @param [uid] uid
 * @param [oauthToken] oauthToken
 * @param [oauthTokenSecret] oauthTokenSecret
 */
@Table
data class User(
    @PrimaryKey
    var qqId: String,
    var uid: String,
    var oauthToken: String,
    var oauthTokenSecret: String
)