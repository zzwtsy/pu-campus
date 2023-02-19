package cn.zzwtsy.pu.init;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.database.DataBaseHelper;

import java.sql.SQLException;

/**
 * 初始化数据库
 *
 * @author zzwtsy
 * @since 2022/12/24
 */
public class InitDataBase {
    /**
     * 初始化数据库
     */
    public void initDataBase() {
        //创建 user 表
        String createUserTableSql = """
                CREATE TABLE "user" (
                  "qqId" TEXT NOT NULL,
                  "uid" TEXT NOT NULL,
                  "oauthToken" TEXT NOT NULL,
                  "oauthTokenSecret" TEXT NOT NULL,
                  PRIMARY KEY ("qqId")
                );""";
        try {
            DataBaseHelper.executeUpdate(createUserTableSql);
        } catch (SQLException e) {
            PuCampus.INSTANCE.getLogger().error("创建user表失败", e);
        }
        //创建 event 表
        String createEventTableSql = """
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
                );""";
        try {
            DataBaseHelper.executeUpdate(createEventTableSql);
        } catch (SQLException e) {
            PuCampus.INSTANCE.getLogger().error("创建event表失败", e);
        }
    }
}
