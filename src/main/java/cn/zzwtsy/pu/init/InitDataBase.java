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
        String createUserTableSql = "CREATE TABLE \"user\" (\n" +
                "  \"qqId\" INTEGER(12) NOT NULL,\n" +
                "  \"uid\" TEXT NOT NULL,\n" +
                "  \"oauthToken\" TEXT NOT NULL,\n" +
                "  \"oauthTokenSecret\" TEXT NOT NULL,\n" +
                "  PRIMARY KEY (\"qqId\")\n" +
                ");";
        try {
            DataBaseHelper.executeUpdate(createUserTableSql);
        } catch (SQLException e) {
            PuCampus.INSTANCE.getLogger().error("创建user表失败", e);
        }
        //创建 event 表
        String createEventTableSql = "CREATE TABLE \"event\" (\n" +
                "  \"id\" INTEGER(7) NOT NULL,\n" +
                "  \"title\" TEXT NOT NULL,\n" +
                "  \"sTime\" DATE NOT NULL,\n" +
                "  \"eTime\" DATE NOT NULL,\n" +
                "  \"startline\" DATE NOT NULL,\n" +
                "  \"deadline\" DATE NOT NULL,\n" +
                "  \"address\" TEXT NOT NULL,\n" +
                "  \"description\" TEXT NOT NULL,\n" +
                "  \"creditName\" TEXT NOT NULL,\n" +
                "  \"isNeedSignOut\" INTEGER(2) NOT NULL CHECK(isNeedSignOut = 0 OR isNeedSignOut = 1),\n" +
                "  PRIMARY KEY (\"id\")\n" +
                ");";
        try {
            DataBaseHelper.executeUpdate(createEventTableSql);
        } catch (SQLException e) {
            PuCampus.INSTANCE.getLogger().error("创建event表失败", e);
        }
    }
}
