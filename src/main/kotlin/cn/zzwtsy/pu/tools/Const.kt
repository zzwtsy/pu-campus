package cn.zzwtsy.pu.tools

import cn.zzwtsy.pu.PuCampus

/** sqlite Driver Name */
const val DRIVER_NAME = "org.sqlite.JDBC"

/** 数据库名字 */
const val DB_NAME = "pu.db"

/** 数据库路径 */
val DB_PATH = "data/${PuCampus.INSTANCE.dataFolder.name}"

/** sql连接url */
val SQL_CONNECT_URL = "jdbc:sqlite:${DB_PATH}"

/** user 表建表 sql 语句 */
val USER_TABLE_SQL =
    """
    CREATE TABLE "user" (
         "qqId" TEXT NOT NULL,
         "uid" TEXT NOT NULL,
         "oauthToken" TEXT NOT NULL,
         "oauthTokenSecret" TEXT NOT NULL,
         PRIMARY KEY ("qqId")
       );
    """.trimIndent()

/** event 表建表 sql 语句 */
val EVENT_TABLE_SQL =
    """
    CREATE TABLE "event" (
      "id" INTEGER(7) NOT NULL,
      "title" TEXT NOT NULL,
      "sTime" DATE NOT NULL,
      "eTime" DATE NOT NULL,
      "startline" DATE NOT NULL,
      "deadline" DATE NOT NULL,
      "address" TEXT NOT NULL,
      "description" TEXT NOT NULL,
      "creditName" TEXT NOT NULL,
      "isNeedSignOut" INTEGER(2) NOT NULL CHECK(isNeedSignOut = 0 OR isNeedSignOut = 1),
      PRIMARY KEY ("id")
    );
    """.trimIndent()