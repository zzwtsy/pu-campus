package cn.zzwtsy.pu.dao;

import cn.zzwtsy.pu.bean.User;
import cn.zzwtsy.pu.database.DataBaseHelper;

import java.sql.SQLException;
import java.util.Map;

/**
 * UserDao
 *
 * @author zzwtsy
 * @since 2022/12/23
 */
public class UserDao {
    private final String tableName = "user";

    /**
     * 获取用户信息
     *
     * @param qqId 用户qq号
     * @return {@link User}
     */
    public User getUserByQqId(long qqId) {
        User user = new User();
        String sql = "SELECT * FROM " + tableName + " WHERE qqId = ?";
        try {
            Map<Object, Object> queryMap = DataBaseHelper.executeQueryMap(sql, qqId);
            if (queryMap != null) {
                user.setOauthToken((String) queryMap.get("oauthToken"));
                user.setOauthTokenSecret((String) queryMap.get("oauthTokenSecret"));
                user.setQqId((long) queryMap.get("qqId"));
                user.setUid((String) queryMap.get("uid"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * 添加用户信息
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return 受影响的行数: >=1 添加成功
     */
    public int addUser(long qqId, String uid, String oauthToken, String oauthTokenSecret) {
        int status;
        String sql = "INSERT INTO " + tableName + " (qqId,uid,oauthToken,oauthTokenSecret) VALUES (?,?,?,?)";
        try {
            status = DataBaseHelper.executeUpdate(sql, qqId, uid, oauthToken, oauthTokenSecret);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    /**
     * 删除用户
     *
     * @param qqId 用户qq号
     * @return 受影响的行数: >=1 删除成功
     */
    public int deleteUser(long qqId) {
        int deleteStatus;
        String sql = "DELETE FROM" + tableName + "WHERE qqId = ?";
        try {
            deleteStatus = DataBaseHelper.executeUpdate(sql, qqId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleteStatus;
    }

    /**
     * 更新用户信息
     *
     * @param qqId             qq号
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return 受影响的行数: >=1 更新成功
     */
    public int updateUser(long qqId, String uid, String oauthToken, String oauthTokenSecret) {
        int status;
        String sql = "UPDATE " + tableName + " SET uid = ?,oauthToken = ?,oauthTokenSecret = ? WHERE qqId = ?";
        try {
            status = DataBaseHelper.executeUpdate(sql, uid, oauthToken, oauthTokenSecret, qqId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
}
