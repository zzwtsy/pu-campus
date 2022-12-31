package cn.zzwtsy.pu.tools;

import cn.zzwtsy.pu.service.UserService;

import java.util.regex.Pattern;

import static cn.zzwtsy.pu.tools.MyStatic.setting;

/**
 * 一些检查用户的方法
 *
 * @author zzwtsy
 * @since 2022/12/26
 */
public class CheckUser {
    /**
     * 检查用户是否登录
     *
     * @param qqId 用户qq号
     * @return boolean
     */
    public static boolean checkUserLogin(long qqId) {
        return new UserService().getUser(qqId) != null;
    }

    /**
     * 检查用户qq号是否正确
     *
     * @param qqId qq号
     * @return boolean
     */
    public static boolean checkUserQqId(String qqId) {
        String qqIdFormat = "^[1-9][0-9]{4,10}$";
        return Pattern.matches(qqIdFormat, qqId);
    }

    /**
     * 检查用户是否为管理员
     *
     * @param qqId qq号
     * @return boolean
     */
    public static boolean checkAdminQqId(long qqId) {
        return qqId == setting.getAdminId();
    }
}
