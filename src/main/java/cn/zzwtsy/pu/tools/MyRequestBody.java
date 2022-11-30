package cn.zzwtsy.pu.tools;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * 请求体
 *
 * @author zzwtsy
 * @since 2022/11/30
 */
public class MyRequestBody {
    public static RequestBody loginBody(String userName, String password) {
        return new FormBody.Builder()
                .add("password", password)
                .add("email", userName)
                .add("client", "1")
                .build();
    }
}