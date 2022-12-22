package cn.zzwtsy.pu.dao;

import cn.zzwtsy.pu.bean.User;
import cn.zzwtsy.pu.database.DataBaseHelper;

import java.sql.SQLException;
import java.util.Map;

public class UserDao {
    /**
     * 获取用户信息
     *
     * @param qqId 用户qq号
     * @return {@link User}
     */
    public User getUser(String qqId) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE qqId = ?";
        try {
            Map<Object, Object> queryMap = DataBaseHelper.executeQueryMap(sql, qqId);
            user.setOauthToken((String) queryMap.get("oauthToken"));
            user.setOauthTokenSecret((String) queryMap.get("oauthTokenSecret"));
            user.setQqId((String) queryMap.get("qqId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    /**
     * 添加用户信息
     *
     * @param qqId             qq号
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return int
     */
    public int addUser(String qqId, String oauthToken, String oauthTokenSecret) {
        int status;
        String sql = "INSERT INTO user (qqId,oauthToken,oauthTokenSecret) VALUES (?,?,?)";
        try {
            status = DataBaseHelper.executeUpdate(sql, qqId, oauthToken, oauthTokenSecret);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    /**
     * 更新用户信息
     *
     * @param qqId             qq号
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return int 影响的行数
     */
    public int updateUser(String qqId, String oauthToken, String oauthTokenSecret) {
        int status;
        String sql = "UPDATE user SET oauthToken = ?,oauthTokenSecret = ? WHERE qqId = ?";
        try {
            status = DataBaseHelper.executeUpdate(sql, oauthToken, oauthTokenSecret, qqId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
}
