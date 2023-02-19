package cn.zzwtsy.pu.utils;

import cn.zzwtsy.pu.PuCampus;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密
 *
 * @author zzwtsy
 * @since 2023/01/21
 */
public class Encryption {
    /**
     * md5加密
     *
     * @param password 密码
     * @param salt     盐值
     * @return {@link String}
     */
    public static String encryptionToMd5(String password, String salt) {
        if (password == null || password.isEmpty()) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest((password + salt).getBytes(StandardCharsets.UTF_8));
            // 将处理后的字节转成 16 进制，得到最终 32 个字符
            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            PuCampus.INSTANCE.getLogger().error(e);
            return null;
        }
    }
}
