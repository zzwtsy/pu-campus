package cn.zzwtsy.pu.database

import cn.zzwtsy.pu.PuCampus
import cn.zzwtsy.pu.tools.*
import java.io.File
import java.sql.DriverManager
import java.sql.SQLException

/**
 * 初始化数据库
 * @author zzwtsy
 * @date 2023/03/21
 * @constructor 创建[InitDatabase]
 */
class InitDatabase {

    /**
     * 判断数据库是否存在，不存在则创建 sqlite 数据库和表
     */
    fun init() {
        if (File(DB_PATH).exists() || File("$DB_PATH/$DB_NAME").exists()) {
            return
        }
        //创建目录
        File(DB_PATH).mkdirs()
        PuCampus.INSTANCE.logger.info("数据库不存在，初始化数据库")
        try {
            Class.forName(DRIVER_NAME)
        } catch (e: ClassNotFoundException) {
            PuCampus.INSTANCE.logger.error("连接数据库失败", e)
        }
        try {
            val executeUpdateStatus = DriverManager
                .getConnection(SQL_CONNECT_URL)
                .prepareStatement(USER_TABLE_SQL)
                .executeUpdate()
            if (executeUpdateStatus == 0)
                PuCampus.INSTANCE.logger.info("创建 user 表成功")
            else
                PuCampus.INSTANCE.logger.error("创建 user 表失败")
        } catch (e: SQLException) {
            PuCampus.INSTANCE.logger.error("创建 user 表失败", e)
        }
    }
}