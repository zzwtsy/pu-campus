package cn.zzwtsy.pu.tools;

/**
 * @author zzwtsy
 * @since 2022/11/25
 */
public class DataBaseStatic {
    public static final String DRIVER_CLASS_NAME = "org.sqlite.JDBC";
    public static final String DB_NAME = "pu.db";
    public static final String PLUGIN_DATA_FILE_PATH = "data/cn.zzwtsy.pu/";
    public static final String DB_FILE_FULL_PATH = PLUGIN_DATA_FILE_PATH + DB_NAME;
    public static final String SQL_CONNECT_URL = "jdbc:sqlite:" + DB_FILE_FULL_PATH;
}
