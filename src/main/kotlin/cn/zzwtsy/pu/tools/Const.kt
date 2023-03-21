package cn.zzwtsy.pu.tools

import cn.zzwtsy.pu.PuCampus

const val DRIVER_NAME = "org.sqlite.JDBC"
const val DB_NAME = "pu.db"
val DB_PATH = "data/${PuCampus.INSTANCE.dataFolder.name}"
val SQL_CONNECT_URL = "jdbc:sqlite:${DB_PATH}"
const val USER_TABLE_SQL =
    """
    CREATE TABLE "user" (
                  "qqId" TEXT NOT NULL,
                  "uid" TEXT NOT NULL,
                  "oauthToken" TEXT NOT NULL,
                  "oauthTokenSecret" TEXT NOT NULL,
                  PRIMARY KEY ("qqId")
                );
    """