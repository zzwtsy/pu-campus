package cn.zzwtsy.pu.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.zzwtsy.pu.tools.DataBaseStatic.DRIVER_CLASS_NAME;
import static cn.zzwtsy.pu.tools.DataBaseStatic.SQL_CONNECT_URL;

/**
 * DBUtils
 *
 * @author zzwtsy
 * @since 2022/11/25
 */
public class DataBaseHelper {
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    /**
     * 注册数据库
     */
    public static void registerDataBase() {
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            System.err.println("数据库连接失败");
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库Connection
     *
     * @return connection
     */
    private static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(SQL_CONNECT_URL);
        return conn;
    }

    /**
     * 获取数据库statement
     *
     * @return Statement
     */
    private static PreparedStatement getStatement(String sql) throws SQLException {
        pstmt = getConnection().prepareStatement(sql);
        return pstmt;
    }

    /**
     * 执行sql查询并返回查询结果
     *
     * @param sql    sql语句
     * @param object sql参数
     * @return {@code List<Map<Object, Object>>}
     * @throws SQLException sqlexception异常
     */
    public static List<Map<Object, Object>> executeQueryList(String sql, Object... object) throws SQLException {
        List<Map<Object, Object>> listFromResultSet;
        pstmt = getStatement(sql);
        for (int i = 0; i < object.length; i++) {
            pstmt.setObject(i + 1, object[i]);
        }
        try {
            rs = pstmt.executeQuery();
            listFromResultSet = getListFromResultSet(rs);
        } finally {
            close();
        }
        return listFromResultSet;
    }

    /**
     * 执行sql查询并返回查询结果
     *
     * @param sql    sql
     * @param object 对象
     * @return {@code Object}
     * @throws SQLException sqlexception异常
     */
    public static Map<Object, Object> executeQueryMap(String sql, Object... object) throws SQLException {
        Map<Object, Object> resultFromResultSet;
        pstmt = getStatement(sql);
        System.out.println(object[0]);
        for (int i = 0; i < object.length; i++) {
            pstmt.setObject(i + 1, object[i]);
        }
        try {
            rs = pstmt.executeQuery();
            resultFromResultSet = getResultFromResultSet(rs);
        } finally {
            close();
        }
        return resultFromResultSet;
    }

    /**
     * 执行增删改操作
     *
     * @param sql    sql语句
     * @param object sql参数
     * @return int
     * @throws SQLException sqlexception异常
     */
    public static int executeUpdate(String sql, Object... object) throws SQLException {
        int executeStatus = 0;
        pstmt = getStatement(sql);
        for (int i = 0; i < object.length; i++) {
            pstmt.setObject(i + 1, object[i]);
        }
        try {
            executeStatus = pstmt.executeUpdate();
        } finally {
            close();
        }
        return executeStatus;
    }

    /**
     * 从ResultSet获取列表
     *
     * @param rs ResultSet
     * @return {@code List<Map<Object, Object>>}
     * @throws SQLException sqlexception异常
     */
    private static List<Map<Object, Object>> getListFromResultSet(ResultSet rs) throws SQLException {
        List<Map<Object, Object>> list = new ArrayList<>();
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int columnCount = rsMetaData.getColumnCount();
        while (rs.next()) {
            Map<Object, Object> map = new HashMap<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                String column = rsMetaData.getColumnLabel(i);
                Object object = rs.getObject(column);
                if (object != null) {
                    map.put(column, object);
                }
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 从结果集得到结果
     *
     * @param rs rs
     * @return {@code Object}
     * @throws SQLException sqlexception异常
     */
    private static Map<Object, Object> getResultFromResultSet(ResultSet rs) throws SQLException {
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int columnCount = rsMetaData.getColumnCount();
        Map<Object, Object> map = new HashMap<>(columnCount);
        if (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                String column = rsMetaData.getColumnLabel(i);
                Object object = rs.getObject(column);
                if (object != null) {
                    map.put(column, object);
                }
            }
        } else {
            return null;
        }
        return map;
    }

    private static void close() {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                System.err.println("PreparedStatement关闭失败");
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Connection关闭失败");
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("ResultSet关闭失败");
                e.printStackTrace();
            }
        }
    }

    /**
     * 自动提交事物
     *
     * @param b true or false
     */
    public static void autoCommit(boolean b) {
        try {
            conn.setAutoCommit(b);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交事物
     */
    public static void commit() {
        try {
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 事务回滚
     */
    public static void rollback() {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
