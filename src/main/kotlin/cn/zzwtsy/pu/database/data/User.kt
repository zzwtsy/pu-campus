package cn.zzwtsy.pu.database.data

import org.ktorm.entity.Entity

interface User: Entity<User> {
    companion object: Entity.Factory<User>()
    var qqId: String
    var uid: String
    var oauthToken: String
    var oauthTokenSecret: String
}