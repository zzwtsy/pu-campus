package cn.zzwtsy.pu.database.data

import org.ktorm.database.Database
import org.ktorm.dsl.isNotNull
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.text

object Users : Table<User>("user") {
    val qqId = text("qqId").bindTo { it.qqId }.primaryKey().isNotNull()
    val uid = text("uid").bindTo { it.uid }.isNotNull()
    val oauthToken = text("oauthToken").bindTo { it.oauthToken }.isNotNull()
    val oauthTokenSecret = text("oauthTokenSecret").bindTo { it.oauthTokenSecret }.isNotNull()
}

val Database.users get() = this.sequenceOf(Users)