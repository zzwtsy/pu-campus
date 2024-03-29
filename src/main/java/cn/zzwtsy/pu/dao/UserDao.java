package cn.zzwtsy.pu.dao;

import cn.zzwtsy.pu.PuCampus;
import cn.zzwtsy.pu.bean.UserBean;
import cn.zzwtsy.pu.database.DataBaseHelper;
import cn.zzwtsy.pu.utils.Encryption;

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
    private final int md5Length = 32;

    /**
     * 获取用户信息
     *
     * @param qqId 用户qq号
     * @return {@link UserBean}
     */
    public UserBean getUserByQqId(long qqId) {
        String encryption = encryptionQqId(qqId);
        if (encryption == null || encryption.length() != md5Length) {
            return null;
        }
        UserBean userBean = new UserBean();
        String sql = "SELECT * FROM " + tableName + " WHERE qqId = ?";
        try {
            Map<Object, Object> queryMap = DataBaseHelper.executeQueryMap(sql, encryption);
            if (queryMap != null) {
                userBean.setOauthToken((String) queryMap.get("oauthToken"));
                userBean.setOauthTokenSecret((String) queryMap.get("oauthTokenSecret"));
                userBean.setQqId((String) queryMap.get("qqId"));
                userBean.setUid((String) queryMap.get("uid"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            PuCampus.INSTANCE.getLogger().error("获取用户信息失败", e);
            return null;
        }
        return userBean;
    }

    /**
     * 添加用户信息
     *
     * @param oauthToken       oauthToken
     * @param oauthTokenSecret oauthTokenSecret
     * @return 受影响的行数: >=1 添加成功
     */
    public int addUser(long qqId, String uid, String oauthToken, String oauthTokenSecret) {
        String encryption = encryptionQqId(qqId);
        if (encryption == null || encryption.length() != md5Length) {
            return -1;
        }
        int status;
        String sql = "INSERT INTO " + tableName + " (qqId,uid,oauthToken,oauthTokenSecret) VALUES (?,?,?,?)";
        try {
            status = DataBaseHelper.executeUpdate(sql, encryption, uid, oauthToken, oauthTokenSecret);
        } catch (SQLException e) {
            PuCampus.INSTANCE.getLogger().error("添加用户信息失败", e);
            return -1;
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
        String encryption = encryptionQqId(qqId);
        if (encryption == null || encryption.length() != md5Length) {
            return -1;
        }
        int deleteStatus;
        String sql = "DELETE FROM" + tableName + "WHERE qqId = ?";
        try {
            deleteStatus = DataBaseHelper.executeUpdate(sql, encryption);
        } catch (SQLException e) {
            PuCampus.INSTANCE.getLogger().error("删除用户信息失败", e);
            return -1;
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
        String encryption = encryptionQqId(qqId);
        if (encryption == null || encryption.length() != md5Length) {
            return -1;
        }
        int status;
        String sql = "UPDATE " + tableName + " SET uid = ?,oauthToken = ?,oauthTokenSecret = ? WHERE qqId = ?";
        try {
            status = DataBaseHelper.executeUpdate(sql, uid, oauthToken, oauthTokenSecret, encryption);
        } catch (SQLException e) {
            PuCampus.INSTANCE.getLogger().error("更新用户信息失败", e);
            return -1;
        }
        return status;
    }

    /**
     * 加密 qq 号
     *
     * @param qqId qq 号
     * @return {@link String}
     */
    private String encryptionQqId(long qqId) {
        if (qqId == 0) {
            String id = "0";
            return Encryption.encryptionToMd5(id, id);
        }
        String id = new StringBuilder(String.valueOf(qqId)).reverse().toString();
        String salt = id.substring(0, 4);
        return Encryption.encryptionToMd5(id, salt);
    }
}
