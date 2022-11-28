package cn.zzwtsy.pu.database;

import cn.zzwtsy.pu.PuCampus;

import java.io.File;
import java.sql.SQLException;

import static cn.zzwtsy.pu.tools.DataBaseStatic.DB_FILE_PATH;

/**
 * 初始化数据库
 *
 * @author zzwtsy
 * @since 2022/11/25
 */
public class InitDataBase {
    public void initDataBase() {
        File dataFile = new File(DB_FILE_PATH);
        if (!dataFile.exists()) {
            String creatSchoolInfoTableSql = """
                    CREATE TABLE "T_school_info" (
                    "schoolName" TEXT(30) NOT NULL,
                    "email" TEXT(20) NOT NULL,
                    "schoolId" TEXT(20) NOT NULL,
                    "displayOrder" TEXT(30) NOT NULL,
                    PRIMARY KEY ("schoolName")
                    );""";
            try {
                if (DataBaseHelper.executeUpdate(creatSchoolInfoTableSql) == -1) {
                    PuCampus.INSTANCE.getLogger().error("数据库创建失败");
                } else {
                    PuCampus.INSTANCE.getLogger().info("数据库创建成功");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            PuCampus.INSTANCE.getLogger().info("数据库已存在");
        }
    }
}
